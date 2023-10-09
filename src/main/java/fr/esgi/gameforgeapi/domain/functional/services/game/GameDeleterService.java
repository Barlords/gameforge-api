package fr.esgi.gameforgeapi.domain.functional.services.game;

import fr.esgi.gameforgeapi.domain.functional.exceptions.ResourceNotFoundException;
import fr.esgi.gameforgeapi.domain.functional.models.Game;
import fr.esgi.gameforgeapi.domain.functional.models.User;
import fr.esgi.gameforgeapi.domain.functional.services.TokenControllerService;
import fr.esgi.gameforgeapi.domain.ports.client.game.GameDeleterApi;
import fr.esgi.gameforgeapi.domain.ports.server.GamePersistenceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class GameDeleterService implements GameDeleterApi {

    private final GamePersistenceSpi spi;

    private final TokenControllerService tokenControllerService;

    @Override
    public void delete(UUID userToken, UUID id) {
        User user = tokenControllerService.getUser(userToken);

        Game game = spi.findById(id).orElseThrow(() -> new ResourceNotFoundException(""));

        if (game.getCreatorId().equals(user.getId())) {
            spi.deleteById(id);
        }
    }
}
