package fr.esgi.gameforgeapi.server.mappers;

import fr.esgi.gameforgeapi.domain.functional.models.Message;
import fr.esgi.gameforgeapi.server.entities.MessageEntity;

public interface MessageEntityMapper {

    static Message toDomain(MessageEntity entity) {
        return Message.builder()
                .id(entity.getId())
                .channelId(entity.getChannelId())
                .senderId(entity.getSenderId())
                .message(entity.getMessage())
                .sendDate(entity.getSendDate())
                .build();
    }

    static MessageEntity fromDomain(Message domain) {
        return MessageEntity.builder()
                .id(domain.getId())
                .channelId(domain.getChannelId())
                .senderId(domain.getSenderId())
                .message(domain.getMessage())
                .sendDate(domain.getSendDate())
                .build();
    }

}
