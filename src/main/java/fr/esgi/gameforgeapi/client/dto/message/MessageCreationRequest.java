package fr.esgi.gameforgeapi.client.dto.message;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record MessageCreationRequest(
        @NotNull @JsonProperty("user_token")    String userToken,
        @NotNull @JsonProperty("receiver_id")   String receiverId,
        @NotNull @JsonProperty("content")       String content,
        @NotNull @JsonProperty("send_date")     LocalDate sendDate
) {
}
