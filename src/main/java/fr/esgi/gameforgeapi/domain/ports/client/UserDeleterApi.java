package fr.esgi.gameforgeapi.domain.ports.client;

import java.util.UUID;

public interface UserDeleterApi {

    void deleteById(UUID id);

    void deleteByToken(UUID token);

}
