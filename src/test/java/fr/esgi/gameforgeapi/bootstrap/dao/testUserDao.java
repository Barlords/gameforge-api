package fr.esgi.gameforgeapi.bootstrap.dao;

import fr.esgi.gameforgeapi.server.entities.UserEntity;
import fr.esgi.gameforgeapi.server.repositories.dao.IUserDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
public class testUserDao {

    @Autowired
    private IUserDao dao;

    @Test
    public void testGetAll() {
        List<UserEntity> list = dao.findAll();

    }
}
