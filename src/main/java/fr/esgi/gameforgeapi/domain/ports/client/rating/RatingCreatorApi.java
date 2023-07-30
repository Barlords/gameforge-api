package fr.esgi.gameforgeapi.domain.ports.client.rating;

import fr.esgi.gameforgeapi.domain.functional.models.Message;
import fr.esgi.gameforgeapi.domain.functional.models.Rating;

import java.util.UUID;

public interface RatingCreatorApi {

    Rating create(UUID userToken, Rating rating);

}
