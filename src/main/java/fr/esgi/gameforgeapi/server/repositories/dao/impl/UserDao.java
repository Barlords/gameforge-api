package fr.esgi.gameforgeapi.server.repositories.dao.impl;

import fr.esgi.gameforgeapi.server.entities.UserEntity;
import fr.esgi.gameforgeapi.server.repositories.UserRepository;
import fr.esgi.gameforgeapi.server.repositories.dao.IUserDao;
import fr.esgi.gameforgeapi.server.repositories.dao.impl.GenericDao;
import io.vavr.control.Option;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
@Transactional
public class UserDao extends GenericDao<UserEntity> implements IUserDao {
    @PostConstruct
    public void init(){
        super.setClazz(UserEntity.class);
    }
    public Option<UserEntity> findUserEntityByEmailAndPassword(String email, String password) {
        return null;
    }

    public Option<UserEntity> findUserEntityByPseudoAndPassword(String pseudo, String password) {
        return null;
    }

}