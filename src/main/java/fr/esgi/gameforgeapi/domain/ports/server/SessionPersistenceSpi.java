package fr.esgi.gameforgeapi.domain.ports.server;

import fr.esgi.gameforgeapi.domain.functional.models.Session;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

public interface SessionPersistenceSpi extends PersistenceSpi<Session, UUID> {


    Optional<Session> findLastByUserIdAndQuitTimeIsNull(UUID userId);

    void closeAllUserSessions(LocalDate quitDate, UUID userId);

}
