package fr.esgi.gameforgeapi.client.mappers;

import fr.esgi.gameforgeapi.client.dto.message.MessageDto;
import fr.esgi.gameforgeapi.client.dto.message.MessageSendRequest;
import fr.esgi.gameforgeapi.domain.functional.models.Message;

import java.util.UUID;

public interface MessageDtoMapper {

    static MessageDto toDto(Message domain) {
        return new MessageDto(
                domain.getId(),
                domain.getSenderId(),
                domain.getChannelId(),
                domain.getContent(),
                domain.getSendDate()
        );
    }

    static Message creationRequestToDomain(UUID senderId, MessageSendRequest request) {
        return Message.builder()
                .id(UUID.randomUUID())
                .senderId(request.senderId())
                .channelId(request.channelId())
                .content(request.content())
                .sendDate(request.sendDate())
                .build();
    }

}
