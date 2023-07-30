package fr.esgi.gameforgeapi.domain.ports.server;

import fr.esgi.gameforgeapi.domain.functional.models.Action;

import java.util.List;
import java.util.UUID;

public interface ActionPersistenceSpi extends PersistenceSpi<Action, UUID> {

    List<Action> findByLobbyId(UUID lobbyId);
}
