package fr.esgi.gameforgeapi.server.repositories.dao;

import fr.esgi.gameforgeapi.server.entities.UserEntity;
import fr.esgi.gameforgeapi.server.repositories.UserRepository;
import fr.esgi.gameforgeapi.server.repositories.dao.impl.GenericDao;
import io.vavr.control.Option;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class UserDao extends GenericDao<UserEntity> implements IUserDao {

    public Option<UserEntity> findUserEntityByEmailAndPassword(String email, String password) {
        return null;
    }

    public Option<UserEntity> findUserEntityByPseudoAndPassword(String pseudo, String password) {
        return null;
    }

}
