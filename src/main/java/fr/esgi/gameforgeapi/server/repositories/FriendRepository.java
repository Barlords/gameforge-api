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

    List<FriendEntity> findFriendEntitiesByAcceptedAndUserIdOrFriendId(boolean accepted, UUID userId, UUID friendId);

    @Query("UPDATE FriendEntity f SET f.accepted = true WHERE f.id = ?1")
    @Modifying
    void acceptFriend(UUID id);

    void deleteById(UUID id);

}
