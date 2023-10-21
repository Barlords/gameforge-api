package fr.esgi.gameforgeapi.client.dto.user;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record UserFriendOrNotRequest(
        @NotNull @JsonProperty("string_to_search") String string_to_search,
        @NotNull @JsonProperty("userId") UUID userId
) {
}
