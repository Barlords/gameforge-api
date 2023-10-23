package fr.esgi.gameforgeapi.domain.ports.client.friend;


import java.util.UUID;

public interface FriendDeleterApi {

    void deleteFriend(UUID userToken,UUID friendId);


}
