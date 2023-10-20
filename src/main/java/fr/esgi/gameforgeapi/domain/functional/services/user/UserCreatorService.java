package fr.esgi.gameforgeapi.domain.functional.services.user;

import fr.esgi.gameforgeapi.domain.functional.exceptions.EmailAlreadyUsedException;
import fr.esgi.gameforgeapi.domain.functional.exceptions.PseudoAlreadyUsedException;
import fr.esgi.gameforgeapi.domain.functional.models.User;
import fr.esgi.gameforgeapi.client.services.EmailSenderService;
import fr.esgi.gameforgeapi.domain.ports.client.user.UserCreatorApi;
import fr.esgi.gameforgeapi.domain.ports.server.UserPersistenceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class UserCreatorService implements UserCreatorApi {

    private final UserPersistenceSpi spi;

    private final UserModifierService userModifierService;

    @Override
    public User create(User user) {

        Optional<User> userAlreadyExist = spi.findById(user.getId());
        Optional<User> userWithSameEmail = spi.findByEmail(user.getEmail());
        Optional<User> userWithSamePseudo = spi.findByPseudo(user.getPseudo());

        if(userAlreadyExist.isPresent()) {
            if(userWithSameEmail.isPresent() && !userWithSameEmail.get().getId().equals(userAlreadyExist.get().getId())) {
                throw new EmailAlreadyUsedException("L'email est déjà utilisé");
            }
            if(userWithSamePseudo.isPresent() && !userWithSamePseudo.get().getId().equals(userAlreadyExist.get().getId())) {
                throw new PseudoAlreadyUsedException("Le pseudo est déjà utilisé");
            }
        }
        else {
            if(userWithSameEmail.isPresent()) {
                throw new EmailAlreadyUsedException("L'email est déjà utilisé");
            }
            if(userWithSamePseudo.isPresent()) {
                throw new PseudoAlreadyUsedException("Le pseudo est déjà utilisé");
            }
        }

        user = userModifierService.updateToken(user);
        user = user.withPassword(hashPassword(user.getPassword()));

        return spi.save(user);
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
