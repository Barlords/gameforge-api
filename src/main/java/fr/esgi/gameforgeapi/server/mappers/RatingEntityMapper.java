package fr.esgi.gameforgeapi.server.mappers;

import fr.esgi.gameforgeapi.domain.functional.models.Message;
import fr.esgi.gameforgeapi.domain.functional.models.Rating;
import fr.esgi.gameforgeapi.server.entities.MessageEntity;
import fr.esgi.gameforgeapi.server.entities.RatingEntity;

public interface RatingEntityMapper {

    static Rating toDomain(RatingEntity entity) {
        return Rating.builder()
                .id(entity.getId())
                .userId(entity.getUserId())
                .gameId(entity.getGameId())
                .rate(entity.getRate())
                .build();
    }

    static RatingEntity fromDomain(Rating domain) {
        return RatingEntity.builder()
                .id(domain.getId())
                .userId(domain.getUserId())
                .gameId(domain.getGameId())
                .rate(domain.getRate())
                .build();
    }

}
