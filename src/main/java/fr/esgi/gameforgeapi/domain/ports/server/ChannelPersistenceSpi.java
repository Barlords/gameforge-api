package fr.esgi.gameforgeapi.domain.ports.server;

import fr.esgi.gameforgeapi.domain.functional.models.Channel;
import fr.esgi.gameforgeapi.domain.functional.models.User;
import io.vavr.control.Option;

import java.util.UUID;

public interface ChannelPersistenceSpi extends PersistenceSpi<Channel, UUID> {

    Channel getChannelByLobbyId(UUID lobbyId);
}
