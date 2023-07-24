package fr.esgi.gameforgeapi.domain.functional.services.action;

import fr.esgi.gameforgeapi.domain.functional.models.Action;
import fr.esgi.gameforgeapi.domain.ports.client.action.ActionFinderApi;
import fr.esgi.gameforgeapi.domain.ports.server.ActionPersistenceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class ActionFinderService implements ActionFinderApi {

    private final ActionPersistenceSpi spi;

    @Override
    public List<Action> findAll() {
        return spi.findAll();
    }

    @Override
    public Optional<Action> findById(UUID id) {
        return spi.findById(id);
    }
    @Override
    public List<Action> findByLobbyId(UUID lobbyId) {
        return spi.findByLobbyId(lobbyId);
    }

}
