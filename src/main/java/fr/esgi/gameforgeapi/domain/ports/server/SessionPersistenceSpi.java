package fr.esgi.gameforgeapi.domain.ports.server;

import fr.esgi.gameforgeapi.domain.functional.models.Rank;
import fr.esgi.gameforgeapi.domain.functional.models.Session;

import java.util.UUID;

public interface SessionPersistenceSpi extends PersistenceSpi<Session, UUID> {


}
