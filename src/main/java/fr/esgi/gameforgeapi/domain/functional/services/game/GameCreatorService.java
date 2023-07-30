package fr.esgi.gameforgeapi.domain.functional.services.game;

import fr.esgi.gameforgeapi.domain.functional.exceptions.NameAlreadyUsedException;
import fr.esgi.gameforgeapi.domain.functional.models.Game;
import fr.esgi.gameforgeapi.domain.functional.models.User;
import fr.esgi.gameforgeapi.domain.functional.services.TokenControllerService;
import fr.esgi.gameforgeapi.domain.ports.client.game.GameCreatorApi;
import fr.esgi.gameforgeapi.domain.ports.server.GamePersistenceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class GameCreatorService implements GameCreatorApi {

    private final GamePersistenceSpi spi;

    private final TokenControllerService tokenControllerService;

    @Override
    public Game create(UUID userToken, Game game) {
        User user = tokenControllerService.getUser(userToken);

        Optional<Game> gameWithSameName = spi.findByName(game.getName());

        if (gameWithSameName.isPresent()) {
            throw new NameAlreadyUsedException("Le nom "+game.getName()+" est déjà utilisé pour un autre jeux");
        }

        return spi.save(game.withCreatorId(user.getId()));
    }


}
