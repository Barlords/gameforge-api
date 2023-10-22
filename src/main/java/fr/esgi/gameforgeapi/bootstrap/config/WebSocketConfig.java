package fr.esgi.gameforgeapi.bootstrap.config;

import fr.esgi.gameforgeapi.client.services.websocket.CustomHandshakeInterceptor;
import fr.esgi.gameforgeapi.client.services.websocket.WebSocketHandler;
import fr.esgi.gameforgeapi.domain.functional.models.Session;
import fr.esgi.gameforgeapi.domain.ports.client.lobby.LobbyFinderApi;
import fr.esgi.gameforgeapi.domain.ports.client.session.SessionFinderApi;
import fr.esgi.gameforgeapi.domain.ports.client.session.SessionUpdaterApi;
import fr.esgi.gameforgeapi.domain.ports.client.user.UserFinderApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
@CrossOrigin
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    private SessionUpdaterApi sessionUpdaterApi;

    @Autowired
    private SessionFinderApi sessionFinderApi;

    @Autowired
    private LobbyFinderApi lobbyFinderApi;

    @Autowired
    private UserFinderApi userFinderApi;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(myHandler(), "/ws")
                .addInterceptors(new CustomHandshakeInterceptor())
                .setAllowedOrigins("*");
    }

    @Bean
    public WebSocketHandler myHandler() {
        System.out.println("Websockets server initiating on route /ws");
        return new WebSocketHandler(sessionUpdaterApi, sessionFinderApi, lobbyFinderApi, userFinderApi);
    }
}