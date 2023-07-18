package fr.esgi.gameforgeapi.server.repositories;

import fr.esgi.gameforgeapi.server.entities.FriendEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface FriendRepository extends JpaRepository<FriendEntity, UUID> {

    List<FriendEntity> findFriendEntitiesByUserIdEqualsOrFriendIdEquals(UUID userId, UUID friendId);

    void deleteById(UUID friendId);

}
