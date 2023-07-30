package fr.esgi.gameforgeapi.server.repositories;

import fr.esgi.gameforgeapi.server.entities.ActionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface ActionRepository extends JpaRepository<ActionEntity, UUID> {

    Optional<ActionEntity> findActionEntityById(UUID id);

    List<ActionEntity> findActionEntityByLobbyId(UUID id);

}
