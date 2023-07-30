package fr.esgi.gameforgeapi.server.adapters;

import fr.esgi.gameforgeapi.domain.functional.models.User;
import fr.esgi.gameforgeapi.domain.ports.server.UserPersistenceSpi;
import fr.esgi.gameforgeapi.server.mappers.UserEntityMapper;
import fr.esgi.gameforgeapi.server.repositories.UserRepository;
import fr.esgi.gameforgeapi.server.repositories.dao.IUserDao;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserDatabaseAdapter implements UserPersistenceSpi {

    private final UserRepository repository;

    @Autowired
    private IUserDao userDao;

    @Override
    @Transactional
    public User save(User o) {
        return UserEntityMapper.toDomain(userDao.save(UserEntityMapper.fromDomain(o)));
    }

    @Override
    @Transactional
    public List<User> findAll() {
        return userDao.findAll().stream().map(UserEntityMapper::toDomain).toList();
    }

    @Override
    @Transactional
    public Optional<User> findById(UUID id) {
        return repository.findById(id).map(UserEntityMapper::toDomain);
    }

    @Override
    @Transactional
    public Optional<User> findByToken(UUID token) {
        return repository.findByToken(token).map(UserEntityMapper::toDomain);
    }

    @Override
    @Transactional
    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email).map(UserEntityMapper::toDomain);
    }

    @Override
    @Transactional
    public Optional<User> findByPseudo(String pseudo) {
        return repository.findByPseudo(pseudo).map(UserEntityMapper::toDomain);
    }

    @Override
    @Transactional
    public Optional<User> findUserByEmailAndPassword(String email, String password) {
        return repository.findByEmailAndPassword(email, password).map(UserEntityMapper::toDomain);
    }

    @Override
    @Transactional
    public Optional<User> findUserByPseudoAndPassword(String pseudo, String password) {
        return repository.findByPseudoAndPassword(pseudo, password).map(UserEntityMapper::toDomain);
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteByToken(UUID token) {
        repository.deleteByToken(token);
    }

}
