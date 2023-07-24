package fr.esgi.gameforgeapi.server.mappers;

import fr.esgi.gameforgeapi.domain.functional.models.Game;
import fr.esgi.gameforgeapi.domain.functional.models.User;
import fr.esgi.gameforgeapi.server.entities.GameEntity;
import fr.esgi.gameforgeapi.server.entities.UserEntity;

public interface GameEntityMapper {

    static Game toDomain(GameEntity entity) {
        return Game.builder()
                .id(entity.getId())
                .name(entity.getName())
                .srcPath(entity.getSrcPath())
                .description(entity.getDescription())
                .picturePresentation(entity.getPicturePresentation())
                .creatorId(entity.getCreatorId())
                .creationDate(entity.getCreationDate())
                .build();
    }

    static GameEntity fromDomain(Game domain) {
        return GameEntity.builder()
                .id(domain.getId())
                .name(domain.getName())
                .srcPath(domain.getSrcPath())
                .description(domain.getDescription())
                .picturePresentation(domain.getPicturePresentation())
                .creatorId(domain.getCreatorId())
                .creationDate(domain.getCreationDate())
                .build();
    }

}
