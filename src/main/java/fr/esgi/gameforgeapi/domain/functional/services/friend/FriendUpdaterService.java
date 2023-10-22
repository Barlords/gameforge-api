package fr.esgi.gameforgeapi.domain.functional.services.friend;

import fr.esgi.gameforgeapi.domain.functional.models.Friend;
import fr.esgi.gameforgeapi.domain.functional.models.User;
import fr.esgi.gameforgeapi.domain.functional.services.TokenControllerService;
import fr.esgi.gameforgeapi.domain.ports.client.friend.FriendUpdaterApi;
import fr.esgi.gameforgeapi.domain.ports.server.FriendPersistenceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class FriendUpdaterService implements FriendUpdaterApi {

    private final FriendPersistenceSpi spi;

    private final TokenControllerService tokenControllerService;

    @Override
    public Friend update(Friend friend) {
        return spi.save(friend);
    }

    @Override
    public void acceptFriend(UUID userToken,UUID friendToken) {
        User user = tokenControllerService.getUser(userToken);
        User friend = tokenControllerService.getUser(friendToken);
        spi.acceptFriend(user.getId(),friend.getId());
    }
}
