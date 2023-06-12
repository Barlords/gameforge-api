package fr.esgi.gameforgeapi.server.repositories;

import fr.esgi.gameforgeapi.server.entities.RankEntity;
import fr.esgi.gameforgeapi.server.entities.SessionEntity;
import fr.esgi.gameforgeapi.server.entities.UserEntity;
import io.vavr.control.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface SessionRepository extends JpaRepository<SessionEntity, UUID> {

    List<SessionEntity> findAll();

    Option<SessionEntity> findSessionEntityById(UUID id);

}
