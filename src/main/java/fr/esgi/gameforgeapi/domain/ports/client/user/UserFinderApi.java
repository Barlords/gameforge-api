package fr.esgi.gameforgeapi.domain.ports.client.user;

import fr.esgi.gameforgeapi.domain.functional.models.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserFinderApi {

    List<User> findAll();

    List<User> findByNewsletterSubscribed(boolean subscribed);

    Optional<User> findByToken(UUID token);

    Optional<User> findById(UUID id);

    Optional<User> findByEmail(String email);

    Optional<User> findByPseudo(String pseudo);

    List<User> findActiveUsers();
}
