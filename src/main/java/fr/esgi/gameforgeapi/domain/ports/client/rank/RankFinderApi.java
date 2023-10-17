package fr.esgi.gameforgeapi.domain.ports.client.rank;

import fr.esgi.gameforgeapi.domain.functional.models.Rank;
import fr.esgi.gameforgeapi.domain.functional.models.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RankFinderApi {

    List<Rank> findAll();

    List<Rank> findByGame(UUID gameId);
}
