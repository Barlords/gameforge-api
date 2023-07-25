package fr.esgi.gameforgeapi.client.mappers;

import fr.esgi.gameforgeapi.client.dto.session.SessionCreationRequest;
import fr.esgi.gameforgeapi.client.dto.session.SessionDto;
import fr.esgi.gameforgeapi.domain.functional.models.Session;

import java.time.LocalDate;
import java.util.UUID;

public interface SessionDtoMapper {

    static SessionDto toDto(Session domain) {
        return new SessionDto(
                domain.getId(),
                domain.getUserId(),
                domain.getLobbyId(),
                domain.getQuitDate(),
                domain.getJoinDate()
        );
    }

    static Session creationRequestToDomain(SessionCreationRequest request) {
        return Session.builder()
                .id(UUID.randomUUID())
                .lobbyId(request.lobbyId())
                .userId(request.userToken())
                .joinDate(LocalDate.now())
                .build();
    }

    static Session closeRequestToDomain(SessionCreationRequest request) {
        return Session.builder()
                .quitDate(LocalDate.now())
                .build();
    }
}
