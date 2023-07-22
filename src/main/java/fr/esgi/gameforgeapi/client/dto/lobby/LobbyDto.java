package fr.esgi.gameforgeapi.client.dto.lobby;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.time.LocalDate;
import java.util.UUID;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record LobbyDto(

        UUID id,

        UUID gameId,

        LocalDate creationDate,

        LocalDate start_Date,

        Boolean finished,

        UUID winner_id
) {

}