package fr.esgi.gameforgeapi.domain.functional.services.channel;

import fr.esgi.gameforgeapi.domain.functional.models.Channel;
import fr.esgi.gameforgeapi.domain.functional.models.Lobby;
import fr.esgi.gameforgeapi.domain.functional.models.User;
import fr.esgi.gameforgeapi.domain.ports.client.channel.ChannelCreatorApi;
import fr.esgi.gameforgeapi.domain.ports.server.ChannelPersistenceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.UUID;
@Slf4j
@RequiredArgsConstructor
public class ChannelCreatorService implements ChannelCreatorApi {

    private final ChannelPersistenceSpi spi;

    @Override
    public Channel create(Channel channel) {
        return spi.save(channel);
    }

}
