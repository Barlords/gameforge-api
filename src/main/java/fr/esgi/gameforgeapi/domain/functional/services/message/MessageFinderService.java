package fr.esgi.gameforgeapi.domain.functional.services.message;

import fr.esgi.gameforgeapi.domain.functional.models.Message;
import fr.esgi.gameforgeapi.domain.functional.models.User;
import fr.esgi.gameforgeapi.domain.functional.services.TokenControllerService;
import fr.esgi.gameforgeapi.domain.ports.client.message.MessageFinderApi;
import fr.esgi.gameforgeapi.domain.ports.server.MessagePersistenceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class MessageFinderService implements MessageFinderApi {

    private final MessagePersistenceSpi spi;

    private final TokenControllerService tokenControllerService;

    @Override
    public List<Message> findBySenderId(UUID senderId) {
        return spi.findBySenderId(senderId);
    }

    @Override
    public List<Message> findByChannelId(UUID channelId) {
        return spi.findByChannelId(channelId);
    }


}
