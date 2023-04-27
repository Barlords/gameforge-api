package fr.esgi.gameforgeapi.domain.ports.server;

import fr.esgi.gameforgeapi.domain.functional.models.User;

import java.util.UUID;

public interface UserPersistenceSpi extends PersistenceSpi<User, UUID> {

}
