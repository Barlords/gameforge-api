package fr.esgi.gameforgeapi.client.dto.message;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.UUID;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record MessageDto(
        UUID id,
        UUID channelId,
        UUID senderId,
        String content,
        java.time.LocalDateTime sendDate
) {

}