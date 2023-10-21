package fr.esgi.gameforgeapi.server.repositories.dao;

import fr.esgi.gameforgeapi.domain.functional.models.User;
import fr.esgi.gameforgeapi.server.entities.UserEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;


public interface IUserDao extends IGenericDao<UserEntity> {
    @Transactional
    void print();


    @Transactional
    List<User> findUserByLobbyId(UUID id);
}
