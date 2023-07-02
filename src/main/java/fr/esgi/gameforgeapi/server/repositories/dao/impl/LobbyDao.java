package fr.esgi.gameforgeapi.server.repositories.dao.impl;

import fr.esgi.gameforgeapi.server.entities.LobbyEntity;
import fr.esgi.gameforgeapi.server.repositories.dao.ILobbyDao;
import org.springframework.stereotype.Repository;

@Repository
public class LobbyDao extends GenericDao<LobbyEntity> implements ILobbyDao {
}
