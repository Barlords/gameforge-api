package fr.esgi.gameforgeapi.domain.functional.services.Lobby;

import fr.esgi.gameforgeapi.domain.functional.models.Lobby;
import fr.esgi.gameforgeapi.domain.ports.client.lobby.LobbyCreatorApi;
import fr.esgi.gameforgeapi.domain.ports.server.LobbyPersistenceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class LobbyCreatorService implements LobbyCreatorApi {

    private final LobbyPersistenceSpi spi;


    @Override
    public Lobby create(Lobby lobby) {
        return spi.save(lobby);
    }


}
