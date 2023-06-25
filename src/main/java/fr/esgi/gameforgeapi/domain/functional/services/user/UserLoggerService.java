package fr.esgi.gameforgeapi.domain.functional.services.user;

import fr.esgi.gameforgeapi.domain.functional.models.User;
import fr.esgi.gameforgeapi.domain.ports.client.UserLoggerApi;
import fr.esgi.gameforgeapi.domain.ports.server.UserPersistenceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class UserLoggerService implements UserLoggerApi {

    private final UserPersistenceSpi spi;

    @Override
    public Optional<User> login(String login, String password) {
        Optional<User> logWithPseudo = spi.findUserByPseudoAndPassword(login, password);
        Optional<User> logWithEmail = spi.findUserByEmailAndPassword(login, password);
        if (!logWithPseudo.isEmpty()) return logWithPseudo;
        else return logWithEmail;
    }
}
