package fr.esgi.gameforgeapi.domain.functional.services.game;

import fr.esgi.gameforgeapi.domain.functional.exceptions.NameAlreadyUsedException;
import fr.esgi.gameforgeapi.domain.functional.models.Game;
import fr.esgi.gameforgeapi.domain.ports.client.game.GameCreatorApi;
import fr.esgi.gameforgeapi.domain.ports.server.GamePersistenceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class GameCreatorService implements GameCreatorApi {

    private final GamePersistenceSpi spi;


    @Override
    public Game create(Game game) {
        Optional<Game> gameWithSameName = spi.findByName(game.getName());

        if (gameWithSameName.isPresent()) {
            throw new NameAlreadyUsedException("Le nom "+game.getName()+" est déjà utilisé pour un autre jeux");
        }

        return spi.save(game);
    }


}
