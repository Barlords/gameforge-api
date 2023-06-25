package fr.esgi.gameforgeapi.server.repositories.dao.impl;

import fr.esgi.gameforgeapi.server.entities.LobbyEntity;
import fr.esgi.gameforgeapi.server.entities.UserEntity;
import fr.esgi.gameforgeapi.server.repositories.UserRepository;
import fr.esgi.gameforgeapi.server.repositories.dao.IGenericDao;
import fr.esgi.gameforgeapi.server.repositories.dao.ILobbyDao;
import fr.esgi.gameforgeapi.server.repositories.dao.impl.GenericDao;
import io.vavr.control.Option;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class LobbyDao extends GenericDao<LobbyEntity> implements ILobbyDao {
}
