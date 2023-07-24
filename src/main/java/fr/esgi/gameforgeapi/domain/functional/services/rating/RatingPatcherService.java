package fr.esgi.gameforgeapi.domain.functional.services.rating;

import fr.esgi.gameforgeapi.domain.functional.exceptions.ApplicationErrorException;
import fr.esgi.gameforgeapi.domain.functional.exceptions.ResourceNotFoundException;
import fr.esgi.gameforgeapi.domain.functional.models.Rating;
import fr.esgi.gameforgeapi.domain.functional.models.User;
import fr.esgi.gameforgeapi.domain.functional.services.TokenControllerService;
import fr.esgi.gameforgeapi.domain.ports.client.rating.RatingCreatorApi;
import fr.esgi.gameforgeapi.domain.ports.client.rating.RatingPatcherApi;
import fr.esgi.gameforgeapi.domain.ports.server.RatingPersistenceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class RatingPatcherService implements RatingPatcherApi {

    private final RatingPersistenceSpi spi;

    private final TokenControllerService tokenControllerService;

    @Override
    public Rating patch(UUID userToken, Rating rating) {
        User user = tokenControllerService.getUser(userToken);

        Rating oldRating = spi.findById(rating.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Aucun rating correspondant à l'id "+rating.getId()));

        if (oldRating.getUserId().equals(user.getId())) {
            return spi.save(oldRating.withRate(rating.getRate()));
        }
        else {
            throw new ApplicationErrorException("Le rating n'appartient pas au user");
        }
    }


}
