package fr.esgi.gameforgeapi.domain.functional.services.user;

import fr.esgi.gameforgeapi.domain.functional.exceptions.EmailAlreadyUsedException;
import fr.esgi.gameforgeapi.domain.functional.exceptions.ResourceNotFoundException;
import fr.esgi.gameforgeapi.domain.functional.exceptions.PseudoAlreadyUsedException;
import fr.esgi.gameforgeapi.domain.functional.models.User;
import fr.esgi.gameforgeapi.domain.ports.client.user.UserUpdaterApi;
import fr.esgi.gameforgeapi.domain.ports.server.UserPersistenceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class UserUpdaterService implements UserUpdaterApi {

    private final UserPersistenceSpi spi;

    private final UserModifierService userModifierService;

    @Override
    public User update(User user) {

        User previousUser = spi.findByToken(user.getToken())
                .orElseThrow(() -> new ResourceNotFoundException("Aucun utilisateur ne correspond au token fournit"));

        Optional<User> userWithSameEmail = spi.findByEmail(user.getEmail());
        Optional<User> userWithSamePseudo = spi.findByPseudo(user.getPseudo());

        if(userWithSameEmail.isPresent() && !previousUser.getId().equals(userWithSameEmail.get().getId())) {
            throw new EmailAlreadyUsedException("L'email est déjà utilisé");
        }
        if(userWithSamePseudo.isPresent() && !previousUser.getId().equals(userWithSamePseudo.get().getId())) {
            throw new PseudoAlreadyUsedException("Le pseudo est déjà utilisé");
        }

        User patchedUser = userModifierService.patchUser(previousUser, user);

        return spi.save(patchedUser);
    }
}
