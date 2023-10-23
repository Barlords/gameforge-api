package fr.esgi.gameforgeapi.domain.functional.services.channel;

import fr.esgi.gameforgeapi.domain.functional.models.Channel;
import fr.esgi.gameforgeapi.domain.ports.client.channel.ChannelCreatorApi;
import fr.esgi.gameforgeapi.domain.ports.client.channel.ChannelFinderApi;
import fr.esgi.gameforgeapi.domain.ports.server.ChannelPersistenceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class ChannelFinderService implements ChannelFinderApi {

    private final ChannelPersistenceSpi spi;

    @Override
    public Channel getChannelByLobbyId(UUID lobbyId) {
        return spi.getChannelByLobbyId(lobbyId);
    }

}
