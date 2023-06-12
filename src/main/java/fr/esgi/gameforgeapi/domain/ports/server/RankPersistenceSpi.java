package fr.esgi.gameforgeapi.domain.ports.server;

import fr.esgi.gameforgeapi.domain.functional.models.Message;
import fr.esgi.gameforgeapi.domain.functional.models.Rank;

import java.util.UUID;

public interface RankPersistenceSpi extends PersistenceSpi<Rank, UUID> {


}
