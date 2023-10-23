package fr.esgi.gameforgeapi.client.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.UUID;

public record UserDto(
        @JsonProperty("id") UUID id,
        @JsonProperty("email") String email,
        @JsonProperty("pseudo") String pseudo,
        @JsonProperty("token") UUID token,
        @JsonProperty("tokenDate") LocalDate tokenDate,
        @JsonProperty("newsletterSubscribed") boolean newsletterSubscribed,
        @JsonProperty("playedGames") int playedGames,
        @JsonProperty("wonGames") int wonGames
) { }