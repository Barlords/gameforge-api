package fr.esgi.gameforgeapi.domain.ports.client.friend;

import fr.esgi.gameforgeapi.domain.functional.models.User;

import java.util.List;
import java.util.UUID;

public interface FriendFinderApi {

    List<User> findFriendOf(UUID userId);

    List<User> getAskedRequests(UUID userToken);

    List<User> getSentRequests(UUID userToken);
}
