package fr.esgi.gameforgeapi.client.dto.lobby;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record LobbyCreationRequest(
        @NotNull @JsonProperty("game_id") UUID gameId
) {
}
