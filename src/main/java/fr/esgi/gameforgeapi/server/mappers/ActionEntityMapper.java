package fr.esgi.gameforgeapi.server.mappers;

import fr.esgi.gameforgeapi.domain.functional.models.Action;
import fr.esgi.gameforgeapi.server.entities.ActionEntity;

public interface ActionEntityMapper {

    static Action toDomain(ActionEntity entity) {
        return Action.builder()
                .id(entity.getId())
                .userId(entity.getUserId())
                .actionTime(entity.getActionTime())
                .action(entity.getAction())
                .build();
    }

    static ActionEntity fromDomain(Action domain) {
        return ActionEntity.builder()
                .id(domain.getId())
                .userId(domain.getUserId())
                .actionTime(domain.getActionTime())
                .action(domain.getAction())
                .build();
    }
}
