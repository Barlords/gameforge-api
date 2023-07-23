package fr.esgi.gameforgeapi.domain.functional.services.rating;

import fr.esgi.gameforgeapi.domain.functional.models.Message;
import fr.esgi.gameforgeapi.domain.functional.models.Rating;
import fr.esgi.gameforgeapi.domain.functional.models.User;
import fr.esgi.gameforgeapi.domain.functional.services.TokenControllerService;
import fr.esgi.gameforgeapi.domain.ports.client.message.MessageFinderApi;
import fr.esgi.gameforgeapi.domain.ports.client.rating.RatingFinderApi;
import fr.esgi.gameforgeapi.domain.ports.server.MessagePersistenceSpi;
import fr.esgi.gameforgeapi.domain.ports.server.RatingPersistenceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class RatingFinderService implements RatingFinderApi {

    private final RatingPersistenceSpi spi;

    private final TokenControllerService tokenControllerService;

    @Override
    public List<Rating> findByGameId(UUID gameId) {
        return spi.findByGameId(gameId);
    }

    @Override
    public Optional<Rating> findByUserAndGameId(UUID userToken, UUID gameId) {
        User user = tokenControllerService.getUser(userToken);

        return spi.findByUserIdAndGameId(user.getId(), gameId);
    }
}
