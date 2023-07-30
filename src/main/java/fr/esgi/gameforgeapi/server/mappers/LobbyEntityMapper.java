package fr.esgi.gameforgeapi.server.mappers;

import fr.esgi.gameforgeapi.domain.functional.models.Lobby;
import fr.esgi.gameforgeapi.server.entities.LobbyEntity;

public interface LobbyEntityMapper {

    static Lobby toDomain(LobbyEntity entity) {
        return Lobby.builder()
                .id(entity.getId())
                .gameId(entity.getGameId())
                .creatorId(entity.getCreatorId())
                .name(entity.getName())
                .password(entity.getPassword())
                .creationDate(entity.getCreationDate())
                .startDate(entity.getStartDate())
                .finished(entity.isFinished())
                .winnerId(entity.getWinnerId())
                .build();
    }

    static LobbyEntity fromDomain(Lobby domain) {
        return LobbyEntity.builder()
                .id(domain.getId())
                .gameId(domain.getGameId())
                .creatorId(domain.getCreatorId())
                .name(domain.getName())
                .password(domain.getPassword())
                .creationDate(domain.getCreationDate())
                .startDate(domain.getStartDate())
                .finished(domain.isFinished())
                .winnerId(domain.getWinnerId())
                .build();
    }

}
