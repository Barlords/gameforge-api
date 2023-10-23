package fr.esgi.gameforgeapi.domain.ports.client.channel;

import fr.esgi.gameforgeapi.domain.functional.models.Action;
import fr.esgi.gameforgeapi.domain.functional.models.Channel;

public interface ChannelCreatorApi {

    Channel create(Channel channel);
}
