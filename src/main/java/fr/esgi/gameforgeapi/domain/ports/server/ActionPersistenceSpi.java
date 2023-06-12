package fr.esgi.gameforgeapi.domain.ports.server;

import fr.esgi.gameforgeapi.domain.functional.models.Action;
import fr.esgi.gameforgeapi.domain.functional.models.User;
import io.vavr.control.Option;

import java.util.UUID;

public interface ActionPersistenceSpi extends PersistenceSpi<Action, UUID> {

}
