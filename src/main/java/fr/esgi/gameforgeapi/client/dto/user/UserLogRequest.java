package fr.esgi.gameforgeapi.client.dto.user;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record UserLogRequest(
        String pseudo,
        String password
) {

}
