package fr.esgi.gameforgeapi.domain.ports.server;

import fr.esgi.gameforgeapi.domain.functional.models.Message;

import java.util.List;
import java.util.UUID;

public interface MessagePersistenceSpi extends PersistenceSpi<Message, UUID> {

    List<Message> findDiscussionWith(UUID senderId, UUID friendId);

    List<Message> findBySenderId(UUID senderId);

    List<Message> findByReceiverId(UUID receiverId);

}
