package fr.esgi.gameforgeapi.domain.ports.server;

import fr.esgi.gameforgeapi.domain.functional.models.User;

import java.util.Optional;
import java.util.UUID;

public interface UserPersistenceSpi extends PersistenceSpi<User, UUID> {

    Optional<User> findByToken(UUID token);

    Optional<User> findByEmail(String email);

    Optional<User> findByPseudo(String pseudo);

    Optional<User> findUserByEmailAndPassword(String email, String password);

    Optional<User> findUserByPseudoAndPassword(String pseudo, String password);

    void deleteByToken(UUID token);
}
