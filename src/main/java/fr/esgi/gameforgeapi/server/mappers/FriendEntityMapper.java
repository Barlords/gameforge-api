package fr.esgi.gameforgeapi.server.mappers;

import fr.esgi.gameforgeapi.domain.functional.models.Friend;
import fr.esgi.gameforgeapi.domain.functional.models.User;
import fr.esgi.gameforgeapi.server.entities.FriendEntity;
import fr.esgi.gameforgeapi.server.entities.UserEntity;

public interface FriendEntityMapper {

    static Friend toDomain(FriendEntity entity) {
        return Friend.builder()
                .id(entity.getId())
                .userId(entity.getUserId())
                .friendId(entity.getFriendId())
                .creationDate(entity.getCreationDate())
                .build();
    }

    static FriendEntity fromDomain(Friend domain) {
        return FriendEntity.builder()
                .id(domain.getId())
                .userId(domain.getUserId())
                .friendId(domain.getFriendId())
                .creationDate(domain.getCreationDate())
                .build();
    }

}
