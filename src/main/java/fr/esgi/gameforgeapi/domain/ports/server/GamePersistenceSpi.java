package fr.esgi.gameforgeapi.domain.ports.server;

import fr.esgi.gameforgeapi.domain.functional.models.Channel;
import fr.esgi.gameforgeapi.domain.functional.models.Game;

import java.util.UUID;

public interface GamePersistenceSpi extends PersistenceSpi<Game, UUID> {


}
