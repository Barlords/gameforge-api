package fr.esgi.gameforgeapi.domain.ports.client;

import fr.esgi.gameforgeapi.domain.functional.models.User;
import io.vavr.control.Option;

public interface UserLoggerApi {

    Option<User> login(String login, String password);

}
