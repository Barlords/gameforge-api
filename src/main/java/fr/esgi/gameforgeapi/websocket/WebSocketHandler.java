package fr.esgi.gameforgeapi.websocket;

import lombok.AllArgsConstructor;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.net.http.WebSocket;

public class WebSocketHandler extends TextWebSocketHandler{

    public WebSocketHandler() {

    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // Handle incoming WebSocket messages here
        String payload = message.getPayload();
        session.sendMessage(new TextMessage("Server received: " + payload));
    }
}