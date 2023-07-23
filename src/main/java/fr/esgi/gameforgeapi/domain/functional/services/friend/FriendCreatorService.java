package fr.esgi.gameforgeapi.domain.functional.services.friend;

import fr.esgi.gameforgeapi.domain.functional.exceptions.ApplicationErrorException;
import fr.esgi.gameforgeapi.domain.functional.exceptions.FriendAlreadyExistException;
import fr.esgi.gameforgeapi.domain.functional.exceptions.ResourceNotFoundException;
import fr.esgi.gameforgeapi.domain.functional.exceptions.TokenNotValidException;
import fr.esgi.gameforgeapi.domain.functional.models.Friend;
import fr.esgi.gameforgeapi.domain.functional.models.User;
import fr.esgi.gameforgeapi.domain.ports.client.friend.FriendCreatorApi;
import fr.esgi.gameforgeapi.domain.ports.client.user.UserFinderApi;
import fr.esgi.gameforgeapi.domain.ports.server.FriendPersistenceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class FriendCreatorService implements FriendCreatorApi {

    private final FriendPersistenceSpi spi;

    private final UserFinderApi userFinderApi;

    @Override
    public void create(UUID userToken, String friendPseudo) {

        User user = userFinderApi.findByToken(userToken)
                .orElseThrow(() -> new TokenNotValidException(userToken.toString()));

        List<Friend> friends = spi.findFriendsOf(user.getId());
        
        List<User> userFriends = friends.stream()
                .map(f -> userFinderApi.findById(f.getFriendId())
                        .orElseThrow(() ->new ApplicationErrorException("error")))
                .filter(u -> u.getPseudo().equals(friendPseudo))
                .toList();

        if (!userFriends.isEmpty()) {
            throw new FriendAlreadyExistException(friendPseudo);
        }

        User friend = userFinderApi.findByPseudo(friendPseudo)
                .orElseThrow(() -> new ResourceNotFoundException("L'utilisateur " + friendPseudo + "n'existe pas"));

        System.out.println(user.getId().toString());
        System.out.println(friend.getId().toString());

        spi.save(Friend.builder().userId(user.getId()).friendId(friend.getId()).creationDate(LocalDate.now()).build());

    }


}
