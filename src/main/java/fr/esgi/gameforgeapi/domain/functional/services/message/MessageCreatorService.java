package fr.esgi.gameforgeapi.domain.functional.services.message;

import fr.esgi.gameforgeapi.domain.functional.models.Message;
import fr.esgi.gameforgeapi.domain.functional.models.User;
import fr.esgi.gameforgeapi.domain.functional.services.TokenControllerService;
import fr.esgi.gameforgeapi.domain.ports.client.message.MessageCreatorApi;
import fr.esgi.gameforgeapi.domain.ports.server.MessagePersistenceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class MessageCreatorService implements MessageCreatorApi {

    private final MessagePersistenceSpi spi;

    private final TokenControllerService tokenControllerService;

    @Override
    public Message create(UUID userToken, Message message) {

        User user = tokenControllerService.getUser(userToken);

        return spi.save(message.withSenderId(user.getId()));
    }



}
