package fr.esgi.gameforgeapi.client.dto.rating;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.time.LocalDate;
import java.util.UUID;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record RatingDto(
        UUID id,
        UUID userId,
        UUID gameId,
        int rate
) {

}