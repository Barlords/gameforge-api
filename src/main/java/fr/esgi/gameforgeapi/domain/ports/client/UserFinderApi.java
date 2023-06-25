package fr.esgi.gameforgeapi.domain.ports.client;

import fr.esgi.gameforgeapi.domain.functional.models.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserFinderApi {

    List<User> findAll();

    Optional<User> findByToken(UUID token);

    Optional<User> findById(UUID id);

    Optional<User> findByEmail(String email);

    Optional<User> findByPseudo(String pseudo);
}
