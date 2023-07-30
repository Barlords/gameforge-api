package fr.esgi.gameforgeapi.domain.ports.client.session;

import fr.esgi.gameforgeapi.domain.functional.models.Session;

import java.util.UUID;

public interface SessionUpdaterApi {

    Session update(Session session);

    void closeAllUserSessions(UUID userToken);

}
