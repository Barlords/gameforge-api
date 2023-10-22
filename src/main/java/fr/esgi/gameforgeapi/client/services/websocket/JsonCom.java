package fr.esgi.gameforgeapi.client.services.websocket;

import fr.esgi.gameforgeapi.domain.functional.models.User;
import lombok.Builder;
import lombok.Value;
import lombok.With;

@Builder
public class JsonCom {

    @With
    private String action;
    @With
    private String data;

    @With
    private User user;

    // Constructor
    public JsonCom() {
    }

    public JsonCom(String action, String data) {
        this.action = action;
        this.data = data;
    }

    // Getters and setters

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "JsonCom{" +
                "action='" + action + '\'' +
                ", data='" + data + '\'' +
                ", user=" + user +
                '}';
    }
}