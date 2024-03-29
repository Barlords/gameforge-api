package fr.esgi.gameforgeapi.bootstrap.dao;

import fr.esgi.gameforgeapi.domain.functional.models.User;
import fr.esgi.gameforgeapi.domain.functional.services.session.SessionUpdaterService;
import fr.esgi.gameforgeapi.domain.ports.client.user.UserFinderApi;
import fr.esgi.gameforgeapi.server.entities.UserEntity;
import fr.esgi.gameforgeapi.server.mappers.UserEntityMapper;
import fr.esgi.gameforgeapi.server.repositories.dao.IUserDao;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@SpringBootTest
@Transactional
public class testUserDao {

    @Autowired
    private IUserDao dao;

    @Autowired
    private  UserFinderApi userFinderApi;

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

    @Test
    @Transactional
    public void test() {
        //List<User> user = dao.findUserByLobbyId(UUID.fromString("d9fb5ab7-afa3-485e-b8b9-63dd43976e2e"));
        List<User> u = userFinderApi.findActiveUsersInLobby(UUID.fromString("d9fb5ab7-afa3-485e-b8b9-63dd43976e2e"));
        System.out.println(u);
    }


}
