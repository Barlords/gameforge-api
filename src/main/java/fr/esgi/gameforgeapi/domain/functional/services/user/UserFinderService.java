package fr.esgi.gameforgeapi.domain.functional.services.user;

import fr.esgi.gameforgeapi.domain.functional.models.User;
import fr.esgi.gameforgeapi.domain.ports.client.user.UserFinderApi;
import fr.esgi.gameforgeapi.domain.ports.server.UserPersistenceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class UserFinderService implements UserFinderApi {

    private final UserPersistenceSpi spi;

    @Override
    public List<User> findAll() {
        return spi.findAll();
    }

    @Override
    public List<User> findByNewsletterSubscribed(boolean subscribed) {
        return spi.findByNewsletterSubscribed(subscribed);
    }

    @Override
    public Optional<User> findById(UUID id) {
        return spi.findById(id);
    }

    @Override
    public Optional<User> findByToken(UUID token) {
        return spi.findByToken(token);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return spi.findByEmail(email);
    }

    @Override
    public Optional<User> findByPseudo(String string_to_search) {
        return spi.findByPseudo(string_to_search);
    }

    @Override
    public List<User> findActiveUsers() {
        return spi.findActiveUsers();
    }

    @Override
    public List<User> findUsersByPseudo(String pseudo) {
        return spi.findUsersByPseudo(pseudo);
    }
}
