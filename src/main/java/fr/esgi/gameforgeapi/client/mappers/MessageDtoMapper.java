package fr.esgi.gameforgeapi.client.mappers;

import fr.esgi.gameforgeapi.client.dto.message.MessageDto;
import fr.esgi.gameforgeapi.client.dto.message.MessageCreationRequest;
import fr.esgi.gameforgeapi.client.validator.UuidValidator;
import fr.esgi.gameforgeapi.domain.functional.models.Message;

import java.util.UUID;

public interface MessageDtoMapper {

    static MessageDto toDto(Message domain) {
        return new MessageDto(
                domain.getId(),
                domain.getSenderId(),
                domain.getReceiverId(),
                domain.getContent(),
                domain.getSendDate()
        );
    }

    static Message creationRequestToDomain(MessageCreationRequest request) {
        return Message.builder()
                .id(UUID.randomUUID())
                .receiverId(UuidValidator.validate(request.receiverId()))
                .content(request.content())
                .sendDate(request.sendDate())
                .build();
    }

}
