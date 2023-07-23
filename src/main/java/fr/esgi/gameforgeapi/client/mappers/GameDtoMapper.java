package fr.esgi.gameforgeapi.client.mappers;

import fr.esgi.gameforgeapi.client.dto.game.GameCreationRequest;
import fr.esgi.gameforgeapi.client.dto.game.GameDto;
import fr.esgi.gameforgeapi.domain.functional.models.Game;

import java.time.LocalDate;
import java.util.UUID;

public interface GameDtoMapper {

    static GameDto toDto(Game domain) {
        return new GameDto(
                domain.getId(),
                domain.getName(),
                domain.getDescription(),
                domain.getCreationDate()
        );
    }
    static Game creationRequestToDomain(GameCreationRequest request) {
        return Game.builder()
                .id(UUID.randomUUID())
                .name(request.name())
                .description(request.description())
                .creationDate(LocalDate.now())
                .build();
    }
}
