package fr.esgi.gameforgeapi.server.mappers;

import fr.esgi.gameforgeapi.domain.functional.models.Session;
import fr.esgi.gameforgeapi.domain.functional.models.User;
import fr.esgi.gameforgeapi.server.entities.SessionEntity;

public interface SessionEntityMapper {

    static Session toDomain(SessionEntity entity) {
        return Session.builder()
                .id(entity.getId())
                .userId(entity.getUserId())
                .lobbyId(entity.getLobbyId())
                .joinDate(entity.getJoinDate())
                .quitDate(entity.getQuitDate())
                .build();
    }

    static SessionEntity fromDomain(Session domain) {
        return SessionEntity.builder()
                .id(domain.getId())
                .userId(domain.getUserId())
                .lobbyId(domain.getLobbyId())
                .joinDate(domain.getJoinDate())
                .quitDate(domain.getQuitDate())
                .build();
    }

}
