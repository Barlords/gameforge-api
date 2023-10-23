package fr.esgi.gameforgeapi.client.services.websocket;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;

@Builder
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class JsonCom {
    @JsonProperty("action")

    private String action;
    @JsonProperty("data")

    private String data;
    @JsonProperty("userId")
    private String userId;
    @JsonProperty("lobbyId")
    private String lobbyId;
    @JsonProperty("channelId")
    private String channelId;
    @JsonProperty("token")

    private String token;

    public JsonCom(){}

    public JsonCom(String action, String data, String userId, String lobbyId, String channelId, String token) {
        this.action = action;
        this.data = data;
        this.userId = userId;
        this.lobbyId = lobbyId;
        this.channelId = channelId;
        this.token = token;
    }

    // Getters and Setters (or use Lombok for generating them)

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLobbyId() {
        return lobbyId;
    }

    public void setLobbyId(String lobbyId) {
        this.lobbyId = lobbyId;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "JsonCom{" +
                "action='" + action + '\'' +
                ", data='" + data + '\'' +
                ", userId='" + userId + '\'' +
                ", lobbyId='" + lobbyId + '\'' +
                ", channelId='" + channelId + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
