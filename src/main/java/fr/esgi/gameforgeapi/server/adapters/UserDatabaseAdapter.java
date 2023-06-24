package fr.esgi.gameforgeapi.server.adapters;

import fr.esgi.gameforgeapi.domain.functional.models.User;
import fr.esgi.gameforgeapi.domain.ports.server.UserPersistenceSpi;
import fr.esgi.gameforgeapi.server.entities.UserEntity;
import fr.esgi.gameforgeapi.server.mappers.UserEntityMapper;
import fr.esgi.gameforgeapi.server.repositories.UserRepository;
import fr.esgi.gameforgeapi.server.repositories.dao.IGenericDao;
import fr.esgi.gameforgeapi.server.repositories.dao.UserDao;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserDatabaseAdapter implements UserPersistenceSpi {

    private IGenericDao<UserEntity> dao;

    @Override
    @Transactional
    public User save(User o) {
        return UserEntityMapper.toDomain(dao.save(UserEntityMapper.fromDomain(o)));
    }

    @Override
    @Transactional
    public List<User> findAll() {
        return dao.findAll().stream().map(UserEntityMapper::toDomain).toList();
    }

    @Override
    @Transactional
    public Option<User> findById(UUID id) {

        return Option.of(UserEntityMapper.toDomain(dao.findOne(id)));
    }

    @Override
    public Option<User> findUserByEmailAndPassword(String email, String password) {
        return null;
    }

    @Override
    public Option<User> findUserByPseudoAndPassword(String pseudo, String password) {
        return null;
    }

    /*@Override
    @Transactional
    public Option<User> findUserByEmailAndPassword(String email, String password) {
        return dao.findUserEntityByEmailAndPassword(email, password).map(UserEntityMapper::toDomain);
    }

    @Override
    @Transactional
    public Option<User> findUserByPseudoAndPassword(String pseudo, String password) {
        return repository.findUserEntityByPseudoAndPassword(pseudo, password).map(UserEntityMapper::toDomain);
    }*/

}
