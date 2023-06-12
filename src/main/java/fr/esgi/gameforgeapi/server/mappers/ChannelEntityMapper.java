package fr.esgi.gameforgeapi.server.mappers;


import fr.esgi.gameforgeapi.domain.functional.models.Channel;
import fr.esgi.gameforgeapi.server.entities.ChannelEntity;

public interface ChannelEntityMapper {

    static Channel toDomain(ChannelEntity entity) {
        return Channel.builder()
                .id(entity.getId())
                .friendId1(entity.getFriend1_id())
                .friendId2(entity.getFriend2_id())
                .creationDate(entity.getCreationDate())
                .token(entity.getToken())
                .build();
    }

    static ChannelEntity fromDomain(Channel domain) {
        return ChannelEntity.builder()
                .id(domain.getId())
                .friend1_id(domain.getFriendId1())
                .friend2_id(domain.getFriendId2())
                .creationDate(domain.getCreationDate())
                .token(domain.getToken())
                .build();
    }

}
