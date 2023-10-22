package fr.esgi.gameforgeapi.client.services.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.esgi.gameforgeapi.domain.functional.models.Lobby;
import fr.esgi.gameforgeapi.domain.functional.models.Session;
import fr.esgi.gameforgeapi.domain.functional.models.User;
import fr.esgi.gameforgeapi.domain.functional.services.session.SessionUpdaterService;
import fr.esgi.gameforgeapi.domain.ports.client.lobby.LobbyFinderApi;
import fr.esgi.gameforgeapi.domain.ports.client.session.SessionFinderApi;
import fr.esgi.gameforgeapi.domain.ports.client.session.SessionUpdaterApi;
import fr.esgi.gameforgeapi.domain.ports.client.user.UserFinderApi;
import fr.esgi.gameforgeapi.domain.ports.server.SessionPersistenceSpi;
import fr.esgi.gameforgeapi.server.adapters.SessionDatabaseAdapter;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.units.qual.A;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class WebSocketHandler extends TextWebSocketHandler {
    private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    private final SessionUpdaterApi sessionUpdaterApi;

    private final SessionFinderApi sessionFinderApi;

    private final LobbyFinderApi lobbyFinderApi;

    private final UserFinderApi userFinderApi;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws IOException {
        List<String> tokenList = (List<String>) session.getAttributes().get("usertoken");
        String token = tokenList.get(0);
        sessions.put(token, session);
        if(token.equals("0")) {
            sendMessageToUser("0", JsonCom.builder().action("info").data("Main WebSocket Connected").build());
            System.out.println("Main Websocket connected");
        } else {
            System.out.println("Connected client socket | token: " + token);
        }
    }


    @Override
    public void afterConnectionClosed(WebSocketSession session, @NotNull CloseStatus status) throws Exception {
        List<String> tokenList = (List<String>) session.getAttributes().get("usertoken");
        String token = tokenList.get(0);
        sessions.remove(token);
        if(!token.contentEquals("0")) {
            Lobby l = sessionUpdaterApi.closeCurrentSessionIfNecessary(token);
            if(l != null) {
                sendMessageToLobby(l.getId(), JsonCom.builder().action("refreshUser").build());
            }
        }
        super.afterConnectionClosed(session, status);
    }

    public void sendMessageToUser(String userToken, JsonCom message) throws IOException {
        WebSocketSession session = sessions.get(userToken);
        if (session != null && session.isOpen()) {
            session.sendMessage(new TextMessage(toJson(message)));
        }
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonCom json = mapper.readValue(message.asBytes(), JsonCom.class);
        parseAction(json);

    }

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String toJson(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException("Failed to serialize object to JSON", e);
        }
    }

    private void sendMessageToLobby(UUID lobbyId, JsonCom message) throws IOException {
        List<Session> lobbySessions = sessionFinderApi.findByLobbyId(lobbyId);
        for(Session s:lobbySessions) {
            Optional<User> ou = userFinderApi.findById(s.getUserId());
            if(ou.isPresent()) {
                sendMessageToUser(ou.get().getToken().toString(), message);
            }
        }
    }

    private void parseAction(JsonCom json) throws IOException {
        switch (json.getAction()) {
            case "refreshUser":
                sendMessageToLobby(UUID.fromString(json.getData()), JsonCom.builder().action("refreshUser").build());
                break;

        }
    }
}