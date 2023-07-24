package fr.esgi.gameforgeapi.domain.ports.client.rating;

import fr.esgi.gameforgeapi.domain.functional.models.Rating;

import java.util.UUID;

public interface RatingPatcherApi {

    Rating patch(UUID userToken, Rating rating);

}
