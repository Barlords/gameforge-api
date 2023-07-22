package fr.esgi.gameforgeapi.client.mappers;

import fr.esgi.gameforgeapi.client.dto.lobby.LobbyCreationRequest;
import fr.esgi.gameforgeapi.client.dto.lobby.LobbyDto;
import fr.esgi.gameforgeapi.domain.functional.models.Lobby;

import java.time.LocalDate;
import java.util.UUID;

public interface LobbyDtoMapper {

    static LobbyDto toDto(Lobby domain) {
        return new LobbyDto(
                domain.getId(),
                domain.getGameId(),
                domain.getCreationDate(),
                domain.getStartDate(),
                domain.isFinished(),
                domain.getWinnerId()
        );
    }

    static Lobby creationRequestToDomain(LobbyCreationRequest request) {
        return Lobby.builder()
                .id(UUID.randomUUID())
                .gameId(request.gameId())
                .creationDate(LocalDate.now())
                .build();
    }
}