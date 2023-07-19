package fr.esgi.gameforgeapi.domain.ports.server;

import fr.esgi.gameforgeapi.domain.functional.models.Friend;

import java.util.List;
import java.util.UUID;

public interface FriendPersistenceSpi extends PersistenceSpi<Friend, UUID> {

    List<Friend> findFriendsOf(UUID userId);
}