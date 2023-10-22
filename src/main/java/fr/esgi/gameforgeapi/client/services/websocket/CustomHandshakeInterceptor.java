package fr.esgi.gameforgeapi.client.services.websocket;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CustomHandshakeInterceptor implements HandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
                                   WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        String query = request.getURI().getQuery();
        if (query != null && !query.isEmpty()) {
            String[] params = query.split("&");

            for (String param : params) {
                String[] keyValue = param.split("=");
                if (keyValue.length > 1) { // ensure the key-value pair exists
                    String key = keyValue[0];
                    List<String> temp = new ArrayList<String>();
                    temp.add(keyValue[1]);
                    attributes.put(key, temp); // store in the attributes map
                }
            }
        }
        attributes.putAll(request.getHeaders());

        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
                               WebSocketHandler wsHandler, Exception exception) {
    }
}