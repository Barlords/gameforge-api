package fr.esgi.gameforgeapi.client.dto.action;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.time.LocalDate;
import java.util.UUID;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record ActionDto(
        UUID id,
        UUID lobbyId,
        UUID userId,
        LocalDate actionTime,
        String action
) {

}
