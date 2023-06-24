package fr.esgi.gameforgeapi.bootstrap.dao;

import fr.esgi.gameforgeapi.domain.functional.models.User;
import fr.esgi.gameforgeapi.server.entities.UserEntity;
import fr.esgi.gameforgeapi.server.repositories.dao.IGenericDao;
import fr.esgi.gameforgeapi.server.repositories.dao.IUserDao;
import fr.esgi.gameforgeapi.server.repositories.dao.UserDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class testUserDao {

    private IUserDao dao;

    @Test
    public void testGetAll() {
        List<UserEntity> list = dao.findAll();

    }
}
