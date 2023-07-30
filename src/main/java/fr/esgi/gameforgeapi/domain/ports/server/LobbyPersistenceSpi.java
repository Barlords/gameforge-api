package fr.esgi.gameforgeapi.domain.ports.server;

import fr.esgi.gameforgeapi.domain.functional.models.Game;
import fr.esgi.gameforgeapi.domain.functional.models.Lobby;

import java.util.List;
import java.util.UUID;

public interface LobbyPersistenceSpi extends PersistenceSpi<Lobby, UUID> {

    List<Lobby> findByGame(UUID gameId);

}
