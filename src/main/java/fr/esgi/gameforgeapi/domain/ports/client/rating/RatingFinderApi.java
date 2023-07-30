package fr.esgi.gameforgeapi.domain.ports.client.rating;

import fr.esgi.gameforgeapi.domain.functional.models.Message;
import fr.esgi.gameforgeapi.domain.functional.models.Rating;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RatingFinderApi {

    List<Rating> findByGameId(UUID gameId);

    Optional<Rating> findByUserAndGameId(UUID userToken, UUID gameId);

}
