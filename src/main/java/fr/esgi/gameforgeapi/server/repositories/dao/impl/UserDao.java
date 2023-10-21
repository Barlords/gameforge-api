package fr.esgi.gameforgeapi.server.repositories.dao.impl;

import fr.esgi.gameforgeapi.server.entities.UserEntity;
import fr.esgi.gameforgeapi.server.repositories.dao.IUserDao;
import io.vavr.control.Option;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserDao extends GenericDao<UserEntity> implements IUserDao {
    public UserDao(){
        super.setClazz(UserEntity.class);
    }


    @Override
    @Transactional
    public void print() {
        for(UserEntity u: getCurrentSession ().createQuery("from UserEntity", UserEntity.class).getResultList()) {
            System.out.println(u.getId());
        };
    }



    public Option<UserEntity> findUserEntityByEmailAndPassword(String email, String password) {
        return null;
    }

    public Option<UserEntity> findUserEntityByPseudoAndPassword(String pseudo, String password) {
        return null;
    }


}
