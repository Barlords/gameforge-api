package fr.esgi.gameforgeapi.client.dto.lobby;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record LobbyCreationRequest(
        @NotNull @JsonProperty("user_token") String userToken,
        @NotNull @JsonProperty("game_id") String gameId,
        @NotNull @Size(max = 32, message = "{validation.name.size.too_long}") @JsonProperty("name") String name,
        @NotNull @Size(max = 32, message = "{validation.name.size.too_long}") @JsonProperty("password") String password

) {
}
