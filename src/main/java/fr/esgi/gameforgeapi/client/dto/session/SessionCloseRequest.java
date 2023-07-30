package fr.esgi.gameforgeapi.client.dto.session;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record SessionCloseRequest(
        @NotNull @JsonProperty("user_token") UUID userToken,
        @NotNull @JsonProperty("lobby_id") UUID lobbyId
) {
}
