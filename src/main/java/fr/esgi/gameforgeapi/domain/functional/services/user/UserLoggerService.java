package fr.esgi.gameforgeapi.domain.functional.services.user;

import fr.esgi.gameforgeapi.domain.functional.exceptions.NotFoundUserException;
import fr.esgi.gameforgeapi.domain.functional.models.User;
import fr.esgi.gameforgeapi.domain.functional.services.TokenControllerService;
import fr.esgi.gameforgeapi.domain.ports.client.user.UserLoggerApi;
import fr.esgi.gameforgeapi.domain.ports.server.UserPersistenceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class UserLoggerService implements UserLoggerApi {

    private final UserPersistenceSpi spi;

    private final TokenControllerService tokenControllerService;

    @Override
    public User login(String pseudo, String password) {
        return spi.save(
                tokenControllerService.updateToken(
                        spi.findUserByPseudoAndPassword(pseudo, password)
                                .orElseThrow(() -> new NotFoundUserException("Aucun utilisateur ne correspond au login fournit"))
                )
        );

    }

}
