package fr.esgi.gameforgeapi.client.dto.lobby;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import java.util.UUID;

public record LobbyDto(
        @JsonProperty("id") UUID id,
        @JsonProperty("gameId") UUID gameId,
        @JsonProperty("creatorId") UUID creatorId,
        @JsonProperty("name") String name,
        @JsonProperty("password") String password,
        @JsonProperty("creationDate") LocalDate creationDate,
        @JsonProperty("startDate") LocalDate startDate,
        @JsonProperty("finished") Boolean finished,
        @JsonProperty("winnerId") UUID winnerId
) { }
