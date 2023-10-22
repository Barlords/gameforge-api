package fr.esgi.gameforgeapi.domain.functional.services.user;

import fr.esgi.gameforgeapi.domain.functional.exceptions.AccountNotValidatedException;
import fr.esgi.gameforgeapi.domain.functional.exceptions.ResourceNotFoundException;
import fr.esgi.gameforgeapi.domain.functional.models.User;
import fr.esgi.gameforgeapi.domain.functional.services.TokenControllerService;
import fr.esgi.gameforgeapi.domain.ports.client.user.UserLoggerApi;
import fr.esgi.gameforgeapi.domain.ports.server.UserPersistenceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.Base64;

@Slf4j
@RequiredArgsConstructor
public class UserLoggerService implements UserLoggerApi {

    private final UserPersistenceSpi spi;

    private final TokenControllerService tokenControllerService;

    @Override
    public User login(String pseudo, String password) {
        User user = spi.findUserByPseudoAndPassword(pseudo, hashPassword(password))
                .orElseThrow(() -> new ResourceNotFoundException("Aucun utilisateur ne correspond au pseudo et password fournit"));

        if(!user.isEnabled()) {
           throw new AccountNotValidatedException("Le compte n'as pas été validé");
        }
        if(user.getTokenDate().atStartOfDay().isBefore((LocalDate.now()).atStartOfDay())) {
            return spi.update(tokenControllerService.updateToken(user));
        } else {
            return user;
        }
    }

    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hashedBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error occurred during hashing", e);
        }
    }

}
