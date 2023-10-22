package fr.esgi.gameforgeapi.domain.ports.client.session;

import fr.esgi.gameforgeapi.domain.functional.models.Lobby;
import fr.esgi.gameforgeapi.domain.functional.models.Session;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.UUID;

public interface SessionUpdaterApi {

    Session update(Session session);

    void closeAllUserSessions(UUID userToken);

    Lobby closeCurrentSessionIfNecessary(String token) throws UserPrincipalNotFoundException;

}
