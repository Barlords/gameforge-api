package fr.esgi.gameforgeapi.domain.ports.server;

import fr.esgi.gameforgeapi.domain.functional.models.Session;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

public interface SessionPersistenceSpi extends PersistenceSpi<Session, UUID> {


    @Transactional
    Optional<Session> findLastByUserId(UUID userId);
}
