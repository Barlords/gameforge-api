package fr.esgi.gameforgeapi.domain.ports.client.lobby;

import fr.esgi.gameforgeapi.domain.functional.models.Lobby;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LobbyFinderApi {

    List<Lobby> findAll();

    Optional<Lobby> findById(UUID id);

}
