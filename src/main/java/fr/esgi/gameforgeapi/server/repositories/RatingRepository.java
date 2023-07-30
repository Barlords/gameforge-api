package fr.esgi.gameforgeapi.server.repositories;

import fr.esgi.gameforgeapi.server.entities.RatingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface RatingRepository extends JpaRepository<RatingEntity, UUID> {

    Optional<RatingEntity> findRatingEntityByUserIdAndGameId(UUID userId, UUID gameId);

    List<RatingEntity> findRatingEntitiesByGameId(UUID gameId);

}
