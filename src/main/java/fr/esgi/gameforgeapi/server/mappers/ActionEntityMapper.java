package fr.esgi.gameforgeapi.server.mappers;

import fr.esgi.gameforgeapi.domain.functional.models.Action;
import fr.esgi.gameforgeapi.server.entities.ActionEntity;

public interface ActionEntityMapper {

    static Action toDomain(ActionEntity entity) {
        return Action.builder()
                .id(entity.getId())
                .userId(entity.getUserId())
                .lobbyId(entity.getLobbyId())
                .actionTime(entity.getActionTime())
                .action(entity.getAction())
                .build();
    }

    static ActionEntity fromDomain(Action domain) {
        return ActionEntity.builder()
                .id(domain.getId())
                .userId(domain.getUserId())
                .lobbyId(domain.getLobbyId())
                .actionTime(domain.getActionTime())
                .action(domain.getAction())
                .build();
    }
}
