package fr.esgi.gameforgeapi.domain.functional.services.user;

import fr.esgi.gameforgeapi.domain.functional.models.User;
import fr.esgi.gameforgeapi.domain.ports.client.UserCreatorApi;
import fr.esgi.gameforgeapi.domain.ports.client.UserFinderApi;
import fr.esgi.gameforgeapi.domain.ports.server.UserPersistenceSpi;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
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
    public Option<User> findById(UUID id) {
        return spi.findById(id);
    }
}
