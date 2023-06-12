package fr.esgi.gameforgeapi.client.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record UserLogRequest(
        String login,
        String password
) {

}
