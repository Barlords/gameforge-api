package fr.esgi.gameforgeapi.domain.ports.server;

import fr.esgi.gameforgeapi.domain.functional.models.Action;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface ActionPersistenceSpi extends PersistenceSpi<Action, UUID> {

    @Transactional
    List<Action> findByLobbyId(UUID lobbyId);
}
