package fr.esgi.gameforgeapi.domain.ports.client.game;

import fr.esgi.gameforgeapi.domain.functional.models.Game;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GameFinderApi {

    List<Game> findAll();

    Optional<Game> findById(UUID id);

}
