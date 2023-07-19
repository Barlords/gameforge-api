package fr.esgi.gameforgeapi.server.mappers;


import fr.esgi.gameforgeapi.domain.functional.models.Channel;
import fr.esgi.gameforgeapi.server.entities.ChannelEntity;

public interface ChannelEntityMapper {

    static Channel toDomain(ChannelEntity entity) {
        return Channel.builder()
                .id(entity.getId())
                .creatorId(entity.getCreatorId())
                .creationDate(entity.getCreationDate())
                .build();
    }

    static ChannelEntity fromDomain(Channel domain) {
        return ChannelEntity.builder()
                .id(domain.getId())
                .creatorId(domain.getCreatorId())
                .creationDate(domain.getCreationDate())
                .build();
    }

}
