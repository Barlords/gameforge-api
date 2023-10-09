package fr.esgi.gameforgeapi.client.dto.rank;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.UUID;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record RankDto(
        UUID id,
        UUID playerId,
        int playedGames,
        int wonGames
) {
}
