package fr.esgi.gameforgeapi.domain.functional.services.Lobby;

import fr.esgi.gameforgeapi.domain.functional.models.Lobby;
import fr.esgi.gameforgeapi.domain.ports.client.lobby.LobbyFinderApi;
import fr.esgi.gameforgeapi.domain.ports.server.LobbyPersistenceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class LobbyFinderService implements LobbyFinderApi {

    private final LobbyPersistenceSpi spi;

    @Override
    public List<Lobby> findAll() {
        return spi.findAll();
    }

    @Override
    public Optional<Lobby> findById(UUID id) {
        return spi.findById(id);
    }

}
