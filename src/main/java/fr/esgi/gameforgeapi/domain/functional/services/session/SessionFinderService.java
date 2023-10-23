package fr.esgi.gameforgeapi.domain.functional.services.session;

import fr.esgi.gameforgeapi.domain.functional.models.Session;
import fr.esgi.gameforgeapi.domain.ports.client.session.SessionFinderApi;
import fr.esgi.gameforgeapi.domain.ports.server.SessionPersistenceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class SessionFinderService implements SessionFinderApi {

    private final SessionPersistenceSpi spi;


    @Override
    public List<Session> findAll() {
        return spi.findAll();
    }

    @Override
    public Optional<Session> findById(UUID id) {
        return spi.findById(id);
    }

    @Override
    public Optional<Session> findLastByUserIdAndQuitTimeIsNull(UUID userId) {
        return spi.findLastByUserIdAndQuitTimeIsNull(userId);
    }

    @Override
    public List<Session> findByLobbyId(UUID lobbyId) {
        return spi.findByLobbyId(lobbyId);
    }

}
