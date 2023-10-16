package fr.esgi.gameforgeapi.websocket;

public class JsonCom {

    private String action;
    private String data;

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

    @Override
    public String toString() {
        return "JsonCom{" +
                "action='" + action + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}