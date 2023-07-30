package fr.esgi.gameforgeapi.client.dto.friend;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.UUID;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record FriendDto(
        UUID id,
        String email,
        String pseudo
) {

}
