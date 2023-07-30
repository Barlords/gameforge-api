package fr.esgi.gameforgeapi.client.dto.rating;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record RatingCreationRequest(
        @NotNull @JsonProperty("user_token")    String userToken,
        @NotNull @JsonProperty("game_id")       String gameId,
        @NotNull @Min(1) @Max(5) @JsonProperty("rate")          int rate
) {
}
