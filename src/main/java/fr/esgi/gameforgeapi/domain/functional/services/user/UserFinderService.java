package fr.esgi.gameforgeapi.domain.functional.services.user;

import fr.esgi.gameforgeapi.domain.functional.models.User;
import fr.esgi.gameforgeapi.domain.ports.client.UserFinderApi;
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
    public Optional<User> findByPseudo(String pseudo) {

        return spi.findByPseudo(pseudo);
    }
}
