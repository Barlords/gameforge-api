package fr.esgi.gameforgeapi.domain.ports.client.channel;

import fr.esgi.gameforgeapi.domain.functional.models.Channel;

import java.util.UUID;

public interface ChannelFinderApi {

    Channel getChannelByLobbyId(UUID lobbyId);
}
