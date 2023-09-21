package fr.esgi.gameforgeapi.domain.functional.services.user;

import fr.esgi.gameforgeapi.domain.functional.exceptions.CodeValidationDoesntExistException;
import fr.esgi.gameforgeapi.domain.functional.exceptions.EmailAlreadyUsedException;
import fr.esgi.gameforgeapi.domain.functional.exceptions.PseudoAlreadyUsedException;
import fr.esgi.gameforgeapi.domain.functional.models.User;
import fr.esgi.gameforgeapi.domain.ports.client.user.UserCreatorApi;
import fr.esgi.gameforgeapi.domain.ports.client.user.UserVerifierApi;
import fr.esgi.gameforgeapi.domain.ports.server.UserPersistenceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class UserVerifierService implements UserVerifierApi {

    private final UserPersistenceSpi spi;

    @Override
    public boolean verify(String code) {
        User user = spi.findByVerificationCode(code)
                .orElse(null);

        if (user == null || user.isEnabled()) {
            return false;
        } else {
            user = user.withVerificationCode(null).withEnabled(true);
            spi.save(user);

            return true;
        }
    }


}
