package fr.esgi.gameforgeapi.client.dto.session;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record SessionCreationRequest(
        @NotNull @JsonProperty("user_id") UUID userId,
        @NotNull @JsonProperty("lobby_id") UUID lobbyId
) {
}