package fr.esgi.gameforgeapi.domain.ports.server;

import fr.esgi.gameforgeapi.domain.functional.models.Lobby;
import fr.esgi.gameforgeapi.domain.functional.models.Message;

import java.util.UUID;

public interface MessagePersistenceSpi extends PersistenceSpi<Message, UUID> {


}
