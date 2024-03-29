package fr.esgi.gameforgeapi.client.dto.message;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record MessageCreationRequest(
        @NotNull @JsonProperty("user_token")    String userToken,
        @NotNull @JsonProperty("channel_id")   String channelId,
        @NotNull @JsonProperty("content")       String content,
        @NotNull @JsonProperty("send_date") LocalDateTime sendDate
) {
}
