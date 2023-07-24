package fr.esgi.gameforgeapi.client.dto.action;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record ActionCreationRequest(
        @NotNull @JsonProperty("lobby_id")      String lobbyId,
        @NotNull @JsonProperty("user_id")   String userId,
        @NotNull @JsonProperty("action")     String action
) {
}
