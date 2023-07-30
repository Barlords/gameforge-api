package fr.esgi.gameforgeapi.domain.functional.services.user;

import fr.esgi.gameforgeapi.domain.ports.client.user.UserDeleterApi;
import fr.esgi.gameforgeapi.domain.ports.server.UserPersistenceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class UserDeleterService implements UserDeleterApi {

    private final UserPersistenceSpi spi;

    @Override
    public void deleteById(UUID id) {
        spi.deleteById(id);
    }

    @Override
    public void deleteByToken(UUID token) {
        spi.deleteByToken(token);
    }
}
