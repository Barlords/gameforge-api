package fr.esgi.gameforgeapi.server.mappers;

import fr.esgi.gameforgeapi.domain.functional.models.Lobby;
import fr.esgi.gameforgeapi.domain.functional.models.User;
import fr.esgi.gameforgeapi.server.entities.LobbyEntity;
import fr.esgi.gameforgeapi.server.entities.UserEntity;

public interface LobbyEntityMapper {

    static Lobby toDomain(LobbyEntity entity) {
        return Lobby.builder()
                .id(entity.getId())
                .gameId(entity.getGameId())
                .creationDate(entity.getCreationDate())
                .startDate(entity.getStartDate())
                .finished(entity.isFinished())
                .winner(entity.getWinner())
                .build();
    }

    static LobbyEntity fromDomain(Lobby domain) {
        return LobbyEntity.builder()
                .id(domain.getId())
                .gameId(domain.getGameId())
                .creationDate(domain.getCreationDate())
                .startDate(domain.getStartDate())
                .finished(domain.isFinished())
                .winner(domain.getWinner())
                .build();
    }

}
