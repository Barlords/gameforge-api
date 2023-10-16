package fr.esgi.gameforgeapi.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class WebSocketHandler extends TextWebSocketHandler {
    private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws IOException {
        List<String> tokenList = (List<String>) session.getAttributes().get("usertoken");
        String token = tokenList.get(0);
        sessions.put(token, session);
        if(token.equals("0")) {
            sendMessageToUser("0", "Main websocket connected");
            System.out.println("Main Websocket connected");
        } else {
            System.out.println("Connected client socket | token: " + token);
        }
    }


    @Override
    public void afterConnectionClosed(WebSocketSession session, @NotNull CloseStatus status) throws Exception {
        String userToken = (String) session.getAttributes().get("usertoken");
        sessions.remove(userToken);
        super.afterConnectionClosed(session, status);
    }

    public void sendMessageToUser(String userToken, String message) throws IOException {
        WebSocketSession session = sessions.get(userToken);
        if (session != null && session.isOpen()) {
            session.sendMessage(new TextMessage(message));
        }
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonCom json = mapper.readValue(message.asBytes(), JsonCom.class);
        System.out.println(json.getAction());

    }
}