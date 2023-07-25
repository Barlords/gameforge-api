package fr.esgi.gameforgeapi.client.mappers;

import fr.esgi.gameforgeapi.client.dto.action.ActionCreationRequest;
import fr.esgi.gameforgeapi.client.dto.action.ActionDto;
import fr.esgi.gameforgeapi.client.validator.UuidValidator;
import fr.esgi.gameforgeapi.domain.functional.models.Action;

import java.time.LocalDate;
import java.util.UUID;

public interface ActionDtoMapper {

    static ActionDto toDto(Action domain) {
        return new ActionDto(
                domain.getId(),
                domain.getLobbyId(),
                domain.getUserId(),
                domain.getActionTime(),
                domain.getAction()
        );
    }

    static Action creationRequestToDomain(ActionCreationRequest request) {
        return Action.builder()
                .id(UUID.randomUUID())
                .lobbyId(UuidValidator.validate(request.lobbyId()))
                .userId(UuidValidator.validate(request.userToken()))
                .actionTime(LocalDate.now())
                .action(request.action())
                .build();
    }
}
