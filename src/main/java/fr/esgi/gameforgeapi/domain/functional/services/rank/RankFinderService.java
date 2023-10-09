package fr.esgi.gameforgeapi.domain.functional.services.rank;

import fr.esgi.gameforgeapi.domain.functional.models.Rank;
import fr.esgi.gameforgeapi.domain.functional.models.User;
import fr.esgi.gameforgeapi.domain.ports.client.rank.RankFinderApi;
import fr.esgi.gameforgeapi.domain.ports.client.user.UserFinderApi;
import fr.esgi.gameforgeapi.domain.ports.server.RankPersistenceSpi;
import fr.esgi.gameforgeapi.domain.ports.server.UserPersistenceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class RankFinderService implements RankFinderApi {

    private final RankPersistenceSpi spi;

    @Override
    public List<Rank> findAll() {
        return spi.findAll();
    }

    @Override
    public List<Rank> findByGame(UUID gameId) {
        return spi.findByGame(gameId);
    }
}
