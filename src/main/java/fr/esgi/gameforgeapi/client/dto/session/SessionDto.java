package fr.esgi.gameforgeapi.client.dto.session;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.time.LocalDate;
import java.util.UUID;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record SessionDto(
        UUID id,
        UUID userId,
        UUID lobbyId,
        LocalDate quitDate,
        LocalDate joinDate

) {

}
