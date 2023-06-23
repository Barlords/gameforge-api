package fr.esgi.gameforgeapi.server.mappers;

import fr.esgi.gameforgeapi.domain.functional.models.Rank;
import fr.esgi.gameforgeapi.server.entities.RankEntity;

public interface RankEntityMapper {

    static Rank toDomain(RankEntity entity) {
        return Rank.builder()
                .id(entity.getId())
                .gameId(entity.getGameId())
                .userId(entity.getUserId())
                .playedGame(entity.getPlayedGame())
                .winedGame(entity.getWinedGame())
                .build();
    }

    static RankEntity fromDomain(Rank domain) {
        return RankEntity.builder()
                .id(domain.getId())
                .gameId(domain.getGameId())
                .userId(domain.getUserId())
                .playedGame(domain.getPlayedGame())
                .winedGame(domain.getWinedGame())
                .build();
    }

}
