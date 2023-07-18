package fr.esgi.gameforgeapi.client.dto.message;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record MessageSendRequest(
        @NotNull @JsonProperty("sender_id")    UUID senderId,
        @NotNull @JsonProperty("channel_id")    UUID channelId,
        @NotNull @JsonProperty("content")       String content,
        @NotNull @JsonProperty("sendDate") LocalDate sendDate
) {
}
