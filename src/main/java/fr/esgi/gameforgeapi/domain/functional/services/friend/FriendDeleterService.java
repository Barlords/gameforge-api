package fr.esgi.gameforgeapi.domain.functional.services.friend;

import fr.esgi.gameforgeapi.domain.ports.client.friend.FriendDeleterApi;
import fr.esgi.gameforgeapi.domain.ports.server.FriendPersistenceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class FriendDeleterService implements FriendDeleterApi {

    private final FriendPersistenceSpi spi;

    @Override
    public void deleteById(UUID id) {
        spi.deleteById(id);
    }
}
