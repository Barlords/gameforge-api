package fr.esgi.gameforgeapi.domain.ports.server;

import fr.esgi.gameforgeapi.domain.functional.models.Message;
import fr.esgi.gameforgeapi.domain.functional.models.Rating;

import java.util.List;
import java.util.UUID;

public interface RatingPersistenceSpi extends PersistenceSpi<Rating, UUID> {

    List<Rating> findByGameId(UUID gameId);

}
