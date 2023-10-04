package fr.esgi.gameforgeapi.client.resources;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketRessource {

    @MessageMapping("/send")
    @SendTo("/topic/messages")
    public String broadcastMessage(String message) {
        return message;
    }
}