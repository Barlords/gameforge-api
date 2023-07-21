package fr.esgi.gameforgeapi.domain.ports.client.user;

import fr.esgi.gameforgeapi.domain.functional.models.User;

import java.util.Optional;

public interface UserLoggerApi {

    User login(String pseudo, String password);

}
