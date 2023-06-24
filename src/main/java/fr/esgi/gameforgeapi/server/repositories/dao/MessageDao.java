package fr.esgi.gameforgeapi.server.repositories.dao;

import fr.esgi.gameforgeapi.server.entities.MessageEntity;
import fr.esgi.gameforgeapi.server.entities.RankEntity;
import fr.esgi.gameforgeapi.server.repositories.dao.impl.GenericDao;
import org.springframework.stereotype.Repository;

@Repository
public class MessageDao extends GenericDao<MessageEntity> implements IGenericDao<MessageEntity> {
}
