package fr.esgi.gameforgeapi.domain.ports.client.session;

import fr.esgi.gameforgeapi.domain.functional.models.Session;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SessionFinderApi {

    List<Session> findAll();

    Optional<Session> findById(UUID id);


    Optional<Session> findLastByUserIdAndQuitTimeIsNull(UUID userId);
}
