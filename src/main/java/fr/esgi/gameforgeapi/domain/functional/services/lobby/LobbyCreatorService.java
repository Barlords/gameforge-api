package fr.esgi.gameforgeapi.domain.functional.services.lobby;

import fr.esgi.gameforgeapi.domain.functional.models.Lobby;
import fr.esgi.gameforgeapi.domain.functional.models.User;
import fr.esgi.gameforgeapi.domain.functional.services.TokenControllerService;
import fr.esgi.gameforgeapi.domain.ports.client.lobby.LobbyCreatorApi;
import fr.esgi.gameforgeapi.domain.ports.server.LobbyPersistenceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class LobbyCreatorService implements LobbyCreatorApi {

    private final LobbyPersistenceSpi spi;

    private final TokenControllerService tokenControllerService;


    @Override
    public Lobby create(UUID userToken, Lobby lobby) {
        User user = tokenControllerService.getUser(userToken);

        return spi.save(lobby.withCreatorId(user.getId()));
    }


}
