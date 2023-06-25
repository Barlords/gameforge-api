package fr.esgi.gameforgeapi.bootstrap.dao;

import fr.esgi.gameforgeapi.domain.functional.models.User;
import fr.esgi.gameforgeapi.server.entities.UserEntity;
import fr.esgi.gameforgeapi.server.mappers.UserEntityMapper;
import fr.esgi.gameforgeapi.server.repositories.dao.IUserDao;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@SpringBootTest
@Transactional
public class testUserDao {

    @Autowired
    private IUserDao dao;

    @Test
    @Transactional()
    public void testGetAll() {
        dao.save(
            UserEntityMapper.fromDomain(
                    User.builder()
                            .email("delton.denice@gmail.com")
                            .pseudo("Yuubau")
                            .password("azertyuiop")
                            .tokenDate(LocalDate.now())
                            .token(UUID.randomUUID())
                            .build()
            )
        );
        dao.flush();
        List<UserEntity> list = dao.findAll();
        System.out.println(list);
        dao.save(
                UserEntityMapper.fromDomain(
                        User.builder()
                                .email("lucas@gmail.com")
                                .pseudo("Lulu")
                                .password("azertyuiop")
                                .tokenDate(LocalDate.now())
                                .token(UUID.randomUUID())
                                .build()
                )
        );
        dao.flush();
        list = dao.findAll();
        System.out.println(list);
        Assert.assertEquals("Size", 2, list.size());
    }


}
