package fr.esgi.gameforgeapi.domain.functional.services.user;

import fr.esgi.gameforgeapi.domain.functional.exceptions.AccountNotValidatedException;
import fr.esgi.gameforgeapi.domain.functional.exceptions.ResourceNotFoundException;
import fr.esgi.gameforgeapi.domain.functional.models.User;
import fr.esgi.gameforgeapi.domain.functional.services.TokenControllerService;
import fr.esgi.gameforgeapi.domain.ports.client.user.UserLoggerApi;
import fr.esgi.gameforgeapi.domain.ports.server.UserPersistenceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class UserLoggerService implements UserLoggerApi {

    private final UserPersistenceSpi spi;

    private final TokenControllerService tokenControllerService;

    @Override
    public User login(String pseudo, String password) {
        User user = spi.findUserByPseudoAndPassword(pseudo, password)
                .orElseThrow(() -> new ResourceNotFoundException("Aucun utilisateur ne correspond au pseudo et password fournit"));

        if(!user.isEnabled()) {
           throw new AccountNotValidatedException("Le compte n'as pas été validé");
        }

        return spi.save(tokenControllerService.updateToken(user));
    }

}
