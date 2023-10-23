package fr.esgi.gameforgeapi.client.dto.channel;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;

public record ChannelDto(
        @JsonProperty("id") UUID id,
        @JsonProperty("creatorId") UUID creatorId,
        @JsonProperty("creationDate") String creationDate, // Consider using LocalDate if this is a date.
        @JsonProperty("lobbyId") String lobbyId,           // Consider using UUID if this is a UUID.
        @JsonProperty("friendId") String friendId          // Consider using UUID if this is a UUID.
) { }
