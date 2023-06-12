package fr.esgi.gameforgeapi.domain.functional.services.user;

import fr.esgi.gameforgeapi.domain.functional.models.User;
import fr.esgi.gameforgeapi.domain.ports.client.UserCreatorApi;
import fr.esgi.gameforgeapi.domain.ports.server.UserPersistenceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class UserCreatorService implements UserCreatorApi {

    private final UserPersistenceSpi spi;

    @Override
    public User create(User user) {
        return spi.save(user);
    }
}
