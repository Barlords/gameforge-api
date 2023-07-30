package fr.esgi.gameforgeapi.domain.functional.services.rating;

import fr.esgi.gameforgeapi.domain.functional.models.Message;
import fr.esgi.gameforgeapi.domain.functional.models.Rating;
import fr.esgi.gameforgeapi.domain.functional.models.User;
import fr.esgi.gameforgeapi.domain.functional.services.TokenControllerService;
import fr.esgi.gameforgeapi.domain.ports.client.message.MessageCreatorApi;
import fr.esgi.gameforgeapi.domain.ports.client.rating.RatingCreatorApi;
import fr.esgi.gameforgeapi.domain.ports.server.MessagePersistenceSpi;
import fr.esgi.gameforgeapi.domain.ports.server.RatingPersistenceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class RatingCreatorService implements RatingCreatorApi {

    private final RatingPersistenceSpi spi;

    private final TokenControllerService tokenControllerService;

    @Override
    public Rating create(UUID userToken, Rating rating) {
        User user = tokenControllerService.getUser(userToken);

        return spi.save(rating.withUserId(user.getId()));
    }


}
