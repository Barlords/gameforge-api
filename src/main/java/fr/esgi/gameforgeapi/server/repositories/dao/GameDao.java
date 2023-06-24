package fr.esgi.gameforgeapi.server.repositories.dao;

import fr.esgi.gameforgeapi.server.entities.GameEntity;
import fr.esgi.gameforgeapi.server.entities.LobbyEntity;
import fr.esgi.gameforgeapi.server.repositories.dao.impl.GenericDao;
import org.springframework.stereotype.Repository;

@Repository
public class GameDao extends GenericDao<GameEntity> implements IGenericDao<GameEntity> {
}
