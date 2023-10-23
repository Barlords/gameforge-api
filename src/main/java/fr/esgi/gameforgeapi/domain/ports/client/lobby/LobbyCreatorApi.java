package fr.esgi.gameforgeapi.domain.ports.client.lobby;

import fr.esgi.gameforgeapi.domain.functional.models.Lobby;

import java.util.UUID;

public interface LobbyCreatorApi {

    Lobby create(UUID userToken, Lobby lobby);

    Lobby update(Lobby lobby);
}
