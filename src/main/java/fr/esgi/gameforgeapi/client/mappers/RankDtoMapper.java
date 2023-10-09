package fr.esgi.gameforgeapi.client.mappers;

import fr.esgi.gameforgeapi.client.dto.rank.RankDto;
import fr.esgi.gameforgeapi.domain.functional.models.Rank;

public interface RankDtoMapper {

    static RankDto toDto(Rank domain) {
        return new RankDto(
                domain.getId(),
                domain.getUserId(),
                domain.getPlayedGames(),
                domain.getWonGames()
        );
    }

}
