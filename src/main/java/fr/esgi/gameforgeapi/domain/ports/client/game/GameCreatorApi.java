package fr.esgi.gameforgeapi.domain.ports.client.game;

import fr.esgi.gameforgeapi.domain.functional.models.Game;

public interface GameCreatorApi {

    Game create(Game game);

}
