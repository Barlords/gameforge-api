package fr.esgi.gameforgeapi.domain.functional.services.message;

import fr.esgi.gameforgeapi.domain.functional.exceptions.ApplicationErrorException;
import fr.esgi.gameforgeapi.domain.functional.exceptions.FriendAlreadyExistException;
import fr.esgi.gameforgeapi.domain.functional.exceptions.NotFoundUserException;
import fr.esgi.gameforgeapi.domain.functional.exceptions.TokenNotValidException;
import fr.esgi.gameforgeapi.domain.functional.models.Friend;
import fr.esgi.gameforgeapi.domain.functional.models.Message;
import fr.esgi.gameforgeapi.domain.functional.models.User;
import fr.esgi.gameforgeapi.domain.functional.services.TokenControllerService;
import fr.esgi.gameforgeapi.domain.ports.client.friend.FriendCreatorApi;
import fr.esgi.gameforgeapi.domain.ports.client.message.MessageCreatorApi;
import fr.esgi.gameforgeapi.domain.ports.client.user.UserFinderApi;
import fr.esgi.gameforgeapi.domain.ports.server.FriendPersistenceSpi;
import fr.esgi.gameforgeapi.domain.ports.server.MessagePersistenceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.List;
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
