package fr.esgi.gameforgeapi.domain.ports.client.action;

import fr.esgi.gameforgeapi.domain.functional.models.Action;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ActionFinderApi {

    List<Action> findAll();

    Optional<Action> findById(UUID id);


    List<Action> findByLobbyId(UUID lobbyId);
}
