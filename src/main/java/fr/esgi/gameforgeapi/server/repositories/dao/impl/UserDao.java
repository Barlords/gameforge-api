package fr.esgi.gameforgeapi.server.repositories.dao.impl;

import fr.esgi.gameforgeapi.domain.functional.models.User;
import fr.esgi.gameforgeapi.server.entities.UserEntity;
import fr.esgi.gameforgeapi.server.mappers.UserEntityMapper;
import fr.esgi.gameforgeapi.server.repositories.dao.IUserDao;
import io.vavr.control.Option;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.nio.ByteBuffer;
import java.util.UUID;
import java.util.List;

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

    public static byte[] toBytes(UUID uuid) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(new byte[16]);
        byteBuffer.putLong(uuid.getMostSignificantBits());
        byteBuffer.putLong(uuid.getLeastSignificantBits());
        return byteBuffer.array();
    }

    @Override
    @Transactional
    public List<User> findUserByLobbyId(UUID id) {
        return getCurrentSession ().createQuery(
                "select u from UserEntity u join SessionEntity s on u.id = s.userId join LobbyEntity l on s.lobbyId = l.id" +
                        " where s.quitDate is null and l.id = :id", UserEntity.class)
                .setParameter("id", id).getResultList().stream().map(UserEntityMapper::toDomain).toList();
    }

    @Override
    public User findUserByToken(UUID token) {
        return UserEntityMapper.toDomain(getCurrentSession ().createQuery(
                        "from UserEntity where token = :token", UserEntity.class)
                .setParameter("token", token).uniqueResult());
    }

}
