package fr.esgi.gameforgeapi.domain.ports.server;

import fr.esgi.gameforgeapi.domain.functional.models.User;
import fr.esgi.gameforgeapi.server.entities.UserEntity;
import io.vavr.control.Option;

import java.util.UUID;

public interface UserPersistenceSpi extends PersistenceSpi<User, UUID> {

    Option<User> findUserByEmailAndPassword(String email, String password);

    Option<User> findUserByPseudoAndPassword(String pseudo, String password);

}
