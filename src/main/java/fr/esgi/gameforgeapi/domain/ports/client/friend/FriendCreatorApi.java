package fr.esgi.gameforgeapi.domain.ports.client.friend;

import java.util.UUID;

public interface FriendCreatorApi {

    void create(UUID userToken, UUID friendId);

}
