package fr.esgi.gameforgeapi.domain.functional.services.lobby;

import fr.esgi.gameforgeapi.domain.functional.exceptions.ResourceNotFoundException;
import fr.esgi.gameforgeapi.domain.functional.models.Lobby;
import fr.esgi.gameforgeapi.domain.functional.models.User;
import fr.esgi.gameforgeapi.domain.functional.services.TokenControllerService;
import fr.esgi.gameforgeapi.domain.ports.client.lobby.LobbyCreatorApi;
import fr.esgi.gameforgeapi.domain.ports.client.lobby.LobbyDeleterApi;
import fr.esgi.gameforgeapi.domain.ports.server.LobbyPersistenceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class LobbyDeleterService implements LobbyDeleterApi {

    private final LobbyPersistenceSpi spi;

    private final TokenControllerService tokenControllerService;

    @Override
    public void delete(UUID userToken, UUID id) {

        User user = tokenControllerService.getUser(userToken);

        Lobby lobby = spi.findById(id).orElseThrow(() -> new ResourceNotFoundException(""));

        if (lobby.getCreatorId() == user.getId()) {
            spi.deleteById(id);
        }
    }
}
