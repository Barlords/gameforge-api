package fr.esgi.gameforgeapi.domain.ports.client.friend;


import fr.esgi.gameforgeapi.domain.functional.models.Friend;

import java.util.UUID;

public interface FriendUpdaterApi {

    Friend update(Friend session);

    void acceptFriend(UUID userToken,UUID friendToken);

}
