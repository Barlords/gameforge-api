package fr.esgi.gameforgeapi.domain.ports.client.session;

import fr.esgi.gameforgeapi.domain.functional.models.Session;

public interface SessionCreatorApi {
    Session create(Session session);
}
