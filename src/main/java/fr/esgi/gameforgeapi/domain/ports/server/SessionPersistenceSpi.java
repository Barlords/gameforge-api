package fr.esgi.gameforgeapi.domain.ports.server;

import fr.esgi.gameforgeapi.domain.functional.models.Lobby;
import fr.esgi.gameforgeapi.domain.functional.models.Session;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SessionPersistenceSpi extends PersistenceSpi<Session, UUID> {


    Optional<Session> findLastByUserIdAndQuitTimeIsNull(UUID userId);

    void closeAllUserSessions(LocalDate quitDate, UUID userId);

    List<Session> findByLobbyId(UUID id);

    Lobby closeAllCurrentSessionIfNecessary(String token) throws UserPrincipalNotFoundException;
}
