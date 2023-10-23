package fr.esgi.gameforgeapi.domain.functional.services.friend;

import fr.esgi.gameforgeapi.domain.functional.models.User;
import fr.esgi.gameforgeapi.domain.functional.services.TokenControllerService;
import fr.esgi.gameforgeapi.domain.ports.client.friend.FriendDeleterApi;
import fr.esgi.gameforgeapi.domain.ports.server.FriendPersistenceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class FriendDeleterService implements FriendDeleterApi {

    private final FriendPersistenceSpi spi;

    private final TokenControllerService tokenControllerService;

    @Override
    public void deleteFriend(UUID userToken,UUID friendId) {
        User user = tokenControllerService.getUser(userToken);
        spi.deleteFriend(user.getId(),friendId);
    }
}
