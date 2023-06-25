package fr.esgi.gameforgeapi.domain.ports.client;

import fr.esgi.gameforgeapi.domain.functional.models.User;

import java.util.Optional;

public interface UserLoggerApi {

    Optional<User> login(String login, String password);

}
