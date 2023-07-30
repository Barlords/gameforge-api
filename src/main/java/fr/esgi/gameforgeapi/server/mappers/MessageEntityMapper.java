package fr.esgi.gameforgeapi.server.mappers;

import fr.esgi.gameforgeapi.domain.functional.models.Message;
import fr.esgi.gameforgeapi.server.entities.MessageEntity;

public interface MessageEntityMapper {

    static Message toDomain(MessageEntity entity) {
        return Message.builder()
                .id(entity.getId())
                .receiverId(entity.getReceiverId())
                .senderId(entity.getSenderId())
                .content(entity.getContent())
                .sendDate(entity.getSendDate())
                .build();
    }

    static MessageEntity fromDomain(Message domain) {
        return MessageEntity.builder()
                .id(domain.getId())
                .receiverId(domain.getReceiverId())
                .senderId(domain.getSenderId())
                .content(domain.getContent())
                .sendDate(domain.getSendDate())
                .build();
    }

}
