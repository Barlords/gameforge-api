package fr.esgi.gameforgeapi.domain.ports.client.lobby;

import fr.esgi.gameforgeapi.domain.functional.models.Lobby;

public interface LobbyCreatorApi {

    Lobby create(Lobby lobby);
}
