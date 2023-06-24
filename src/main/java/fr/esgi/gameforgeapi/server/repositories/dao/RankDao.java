package fr.esgi.gameforgeapi.server.repositories.dao;

import fr.esgi.gameforgeapi.server.entities.LobbyEntity;
import fr.esgi.gameforgeapi.server.entities.RankEntity;
import fr.esgi.gameforgeapi.server.repositories.dao.impl.GenericDao;
import org.springframework.stereotype.Repository;

@Repository
public class RankDao extends GenericDao<RankEntity> implements IGenericDao<RankEntity> {
}
