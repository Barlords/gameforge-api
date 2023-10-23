package fr.esgi.gameforgeapi.server.repositories;

import fr.esgi.gameforgeapi.server.entities.FriendEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface FriendRepository extends JpaRepository<FriendEntity, UUID> {

    List<FriendEntity>  findByAcceptedAndUserIdOrAcceptedAndFriendId(boolean accepted,UUID userId, boolean _accepted,UUID friendId);

    @Query("UPDATE FriendEntity f SET f.accepted = true WHERE (f.userId = ?1 AND f.friendId = ?2) OR (f.userId = ?2 AND f.friendId = ?1)")
    @Modifying
    void acceptFriend(UUID userId, UUID friendId);

    @Query("DELETE FROM FriendEntity f WHERE (f.userId = ?1 AND f.friendId = ?2) OR (f.userId = ?2 AND f.friendId = ?1)")
    @Modifying
    void deleteFriend(UUID userId, UUID friendId);

    @Query("SELECT CASE WHEN COUNT(f) > 0 THEN TRUE ELSE FALSE END FROM FriendEntity f WHERE (f.userId = ?1 AND f.friendId = ?2) OR (f.userId = ?2 AND f.friendId = ?1)")
    boolean isFriendOfOrAsked(UUID idUser, UUID idOtherUser);




}
