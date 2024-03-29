package fr.esgi.gameforgeapi.domain.functional.services.friend;

import fr.esgi.gameforgeapi.domain.functional.exceptions.ResourceNotFoundException;
import fr.esgi.gameforgeapi.domain.functional.models.Friend;
import fr.esgi.gameforgeapi.domain.functional.models.User;
import fr.esgi.gameforgeapi.domain.functional.services.TokenControllerService;
import fr.esgi.gameforgeapi.domain.ports.client.friend.FriendFinderApi;
import fr.esgi.gameforgeapi.domain.ports.client.user.UserFinderApi;
import fr.esgi.gameforgeapi.domain.ports.server.FriendPersistenceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class FriendFinderService implements FriendFinderApi {

    private final FriendPersistenceSpi spi;

    private final TokenControllerService tokenControllerService;

    private final UserFinderApi userFinderApi;

    @Override
    public List<User> findFriendOf(UUID userToken) {
        User user = tokenControllerService.getUser(userToken);

        List<Friend> friends = spi.findFriendsOf(user.getId());

        return friends.stream()
                .map(f -> {
                    Optional<User> userFriend;
                    if (f.getUserId().equals(user.getId())) {
                        userFriend = userFinderApi.findById(f.getFriendId());
                    }
                    else {
                        userFriend = userFinderApi.findById(f.getUserId());
                    }
                    return userFriend.orElseThrow(() -> new ResourceNotFoundException("error when map list of Friend"));
                })
                .toList();
    }

    @Override
    public List<User> getAskedRequests(UUID userToken) {
        User user = tokenControllerService.getUser(userToken);

        List<Friend> incomingRequests = spi.findFriendsRequestsOf(user.getId());

        return incomingRequests.stream()
                .filter(f -> f.getFriendId().equals(user.getId()))
                .map(f -> {
                    Optional<User> requestSender = userFinderApi.findById(f.getUserId());
                    return requestSender.orElseThrow(() -> new ResourceNotFoundException("Erreur lors de la récupération des requetes"));
                })
                .toList();
    }

    public List<User> getSentRequests(UUID userToken) {
        User user = tokenControllerService.getUser(userToken);

        List<Friend> outgoingRequests = spi.findFriendsRequestsOf(user.getId());

        return outgoingRequests.stream()
                .filter(f -> f.getUserId().equals(user.getId()))
                .map(f -> {
                    Optional<User> requestReceiver = userFinderApi.findById(f.getFriendId());
                    return requestReceiver.orElseThrow(() -> new ResourceNotFoundException("Erreur lors de la récupération des requetes"));
                })
                .toList();
    }
}
