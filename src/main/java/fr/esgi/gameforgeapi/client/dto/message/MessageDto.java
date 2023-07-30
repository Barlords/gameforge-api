package fr.esgi.gameforgeapi.client.dto.message;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.time.LocalDate;
import java.util.UUID;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record MessageDto(
        UUID id,
        UUID senderId,
        UUID receiverId,
        String content,
        LocalDate sendDate
) {

}