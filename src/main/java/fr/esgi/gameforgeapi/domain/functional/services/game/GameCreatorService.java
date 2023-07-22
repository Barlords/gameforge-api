package fr.esgi.gameforgeapi.domain.functional.services.game;

import fr.esgi.gameforgeapi.domain.functional.models.Game;
import fr.esgi.gameforgeapi.domain.ports.client.game.GameCreatorApi;
import fr.esgi.gameforgeapi.domain.ports.server.GamePersistenceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class GameCreatorService implements GameCreatorApi {

    private final GamePersistenceSpi spi;


    @Override
    public Game create(Game game) {
        return spi.save(game);
    }


}
