package fr.esgi.gameforgeapi.client.dto.friend;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record FriendCreationRequest(
        @NotNull @JsonProperty("user_token") String userToken,
        @NotNull @JsonProperty("friend_id") String friendId
) {
}
