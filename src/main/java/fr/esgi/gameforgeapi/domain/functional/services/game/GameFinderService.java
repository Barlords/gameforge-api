package fr.esgi.gameforgeapi.domain.functional.services.game;

import fr.esgi.gameforgeapi.domain.functional.models.Game;
import fr.esgi.gameforgeapi.domain.ports.client.game.GameFinderApi;
import fr.esgi.gameforgeapi.domain.ports.server.GamePersistenceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class GameFinderService implements GameFinderApi {

    private final GamePersistenceSpi spi;

    @Override
    public List<Game> findAll() {
        return spi.findAll();
    }

    @Override
    public Optional<Game> findById(UUID id) {
        return spi.findById(id);
    }

}
