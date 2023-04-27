package fr.esgi.gameforgeapi.client.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.UUID;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record UserDto(
        UUID id,
        String email,
        String password,
        String pseudo
) {

}
