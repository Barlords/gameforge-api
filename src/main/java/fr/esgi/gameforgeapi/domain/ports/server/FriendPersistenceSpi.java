package fr.esgi.gameforgeapi.domain.ports.server;

import fr.esgi.gameforgeapi.domain.functional.models.Friend;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface FriendPersistenceSpi extends PersistenceSpi<Friend, UUID> {

    List<Friend> findFriendsOf(UUID userId);

    @Transactional
    List<Friend> findFriendsRequestsOf(UUID userId);



    Boolean isFriendOfOrAsked(UUID userId, UUID otherUserId);

    @Transactional
    void acceptFriend(UUID id);

    void deleteById(UUID id);
}
