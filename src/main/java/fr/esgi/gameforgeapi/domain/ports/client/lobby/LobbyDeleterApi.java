package fr.esgi.gameforgeapi.domain.ports.client.lobby;

import fr.esgi.gameforgeapi.domain.functional.models.Lobby;

import java.util.UUID;

public interface LobbyDeleterApi {

    void delete(UUID userToken, UUID id);
}
