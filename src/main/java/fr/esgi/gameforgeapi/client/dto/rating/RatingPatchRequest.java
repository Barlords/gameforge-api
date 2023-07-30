package fr.esgi.gameforgeapi.client.dto.rating;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record RatingPatchRequest(
        @NotNull @JsonProperty("user_token")        String userToken,
        @NotNull @Min(1) @Max(5) @JsonProperty("rate")   int rate
) {
}
