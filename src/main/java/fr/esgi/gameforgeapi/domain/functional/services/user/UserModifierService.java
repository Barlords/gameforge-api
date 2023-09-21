package fr.esgi.gameforgeapi.domain.functional.services.user;

import fr.esgi.gameforgeapi.domain.functional.models.User;
import fr.esgi.gameforgeapi.domain.functional.services.RandomStringGenerator;

import java.time.LocalDate;
import java.util.UUID;

public class UserModifierService {

    public User setId(User user, UUID id) {
        return user.withId(id);
    }

    public User setId(User user, String id) {
        return setId(user, UUID.fromString(id));
    }

    public User setToken(User user, UUID token) {
        return user.withToken(token);
    }

    public User setToken(User user, String token) {
        return setToken(user, UUID.fromString(token));
    }

    public User patchEmail(User user, String newEmail) {
        return user.withEmail(newEmail);
    }

    public User patchPassword(User user, String newPassword) {
        return user.withPassword(newPassword);
    }

    public User patchPseudo(User user, String newPseudo) {
        return user.withPseudo(newPseudo);
    }

    public User patchUser(User user, User newUser) {
        return user.withPseudo(newUser.getPseudo())
                .withPassword(newUser.getPassword())
                .withEmail(newUser.getEmail());
    }

    public User updateToken(User user) {
        return user.withToken(UUID.randomUUID()).withTokenDate(LocalDate.now());
    }

    public User updateVerificationCode(User user) {
        return user.withVerificationCode(RandomStringGenerator.generateAlphanumericString(10));
    }

}
