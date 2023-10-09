package fr.esgi.gameforgeapi.domain.ports.client.message;

import fr.esgi.gameforgeapi.domain.functional.models.Message;

import java.util.List;
import java.util.UUID;

public interface MessageFinderApi {

    List<Message> findBySenderId(UUID senderId);

    List<Message> findByChannelId(UUID channelId);

}
