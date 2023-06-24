package fr.esgi.gameforgeapi.server.repositories.dao;

import fr.esgi.gameforgeapi.server.entities.ChannelEntity;
import fr.esgi.gameforgeapi.server.entities.UserEntity;
import fr.esgi.gameforgeapi.server.repositories.dao.impl.GenericDao;
import io.vavr.control.Option;
import org.springframework.stereotype.Repository;

@Repository
public class ChannelDao extends GenericDao<ChannelEntity> implements IGenericDao<ChannelEntity> {

}
