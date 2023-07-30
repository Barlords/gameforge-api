package fr.esgi.gameforgeapi.domain.ports.client.game;

import fr.esgi.gameforgeapi.domain.functional.models.Game;

import java.util.UUID;

public interface GameDeleterApi {

    void delete(UUID userToken, UUID id);

}
