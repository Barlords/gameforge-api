package fr.esgi.gameforgeapi.domain.ports.client.game;

import fr.esgi.gameforgeapi.domain.functional.models.Game;

import java.util.UUID;

public interface GameCreatorApi {

    Game create(UUID userToken, Game game);

}
