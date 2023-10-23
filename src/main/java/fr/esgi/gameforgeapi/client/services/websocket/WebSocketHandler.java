package fr.esgi.gameforgeapi.client.services.websocket;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.esgi.gameforgeapi.domain.functional.models.*;
import fr.esgi.gameforgeapi.domain.functional.services.session.SessionUpdaterService;
import fr.esgi.gameforgeapi.domain.ports.client.game.GameFinderApi;
import fr.esgi.gameforgeapi.domain.ports.client.lobby.LobbyCreatorApi;
import fr.esgi.gameforgeapi.domain.ports.client.lobby.LobbyFinderApi;
import fr.esgi.gameforgeapi.domain.ports.client.message.MessageCreatorApi;
import fr.esgi.gameforgeapi.domain.ports.client.message.MessageFinderApi;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    private final MessageCreatorApi messageCreatorApi;

    private final MessageFinderApi messageFinderApi;

    private final LobbyCreatorApi lobbyCreatorApi;

    private final GameFinderApi gameFinderApi;


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws IOException, InterruptedException {
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

    public void sendMessageToUser(String userToken, JsonCom message) throws IOException, InterruptedException {
        WebSocketSession session = sessions.get(userToken);
        if(session != null) {
            while (!session.isOpen()) {
                // Wait for the defined interval
                Thread.sleep(300);
            }
            session.sendMessage(new TextMessage(toJson(message)));
        }
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        JsonCom json = objectMapper.readValue(message.asBytes(), JsonCom.class);
        System.out.println(json);
        parseAction(json);

    }

    private static final ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);


    public static String toJson(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException("Failed to serialize object to JSON", e);
        }
    }

    private void sendMessageToLobby(UUID lobbyId, JsonCom message) throws IOException, InterruptedException {
        List<Session> lobbySessions = sessionFinderApi.findByLobbyId(lobbyId);
        for(Session s:lobbySessions) {
            Optional<User> ou = userFinderApi.findById(s.getUserId());
            if(ou.isPresent()) {
                sendMessageToUser(ou.get().getToken().toString(), message);
            }
        }
    }

    private void parseAction(JsonCom json) throws IOException, InterruptedException {
        switch (json.getAction()) {
            case "refreshUser":
                sendMessageToLobby(UUID.fromString(json.getData()), JsonCom.builder().action("refreshUser").build());
                break;
            case "chat":
                messageCreatorApi.create(UUID.fromString(json.getToken()), Message.builder().channelId(UUID.fromString(json.getChannelId())
                ).content(json.getData()).senderId(UUID.fromString(json.getUserId())).sendDate(LocalDateTime.now()).build());
                sendMessageToLobby(UUID.fromString(json.getLobbyId()), json);
                break;
            case "startGame":
                Lobby l = lobbyFinderApi.findById(UUID.fromString(json.getLobbyId())).get();
                lobbyCreatorApi.update(l.withStartDate(LocalDate.now()));
                Game g = gameFinderApi.findById(l.getGameId()).get();
                sendMessageToUser(String.valueOf(0), JsonCom.builder().action("startGame").data(g.getName()).lobbyId(l.getId().toString()).build());
                break;


        }
    }
}