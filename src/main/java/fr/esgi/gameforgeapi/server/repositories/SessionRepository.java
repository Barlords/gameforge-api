package fr.esgi.gameforgeapi.server.repositories;

import fr.esgi.gameforgeapi.server.entities.SessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface SessionRepository extends JpaRepository<SessionEntity, UUID> {

    Optional<SessionEntity> findSessionEntityById(UUID id);

    @Query("SELECT s FROM SessionEntity s WHERE s.quitDate IS NULL AND s.userId = ?1")
    Optional<SessionEntity> findLastByUserIdAndQuitTimeIsNull(UUID userId);

    @Query("UPDATE SessionEntity s SET s.quitDate = ?1 WHERE s.userId = ?2")
    @Modifying
    void closeAllUserSessions(LocalDate quitDate, UUID userId);

    List<SessionEntity> findSessionEntityByLobbyId (UUID id);

}
