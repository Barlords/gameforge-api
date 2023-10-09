package fr.esgi.gameforgeapi.domain.ports.server;

import fr.esgi.gameforgeapi.domain.functional.models.Message;
import fr.esgi.gameforgeapi.domain.functional.models.Rank;
import fr.esgi.gameforgeapi.domain.functional.models.User;

import java.util.List;
import java.util.UUID;

public interface RankPersistenceSpi extends PersistenceSpi<Rank, UUID> {

    List<Rank> findByGame(UUID gameId);

}
