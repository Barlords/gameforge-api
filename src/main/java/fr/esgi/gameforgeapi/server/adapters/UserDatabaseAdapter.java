package fr.esgi.gameforgeapi.server.adapters;

import fr.esgi.gameforgeapi.domain.functional.models.User;
import fr.esgi.gameforgeapi.domain.ports.server.UserPersistenceSpi;
import fr.esgi.gameforgeapi.server.entities.UserEntity;
import fr.esgi.gameforgeapi.server.mappers.UserEntityMapper;
import fr.esgi.gameforgeapi.server.repositories.UserRepository;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserDatabaseAdapter implements UserPersistenceSpi {

    private final UserRepository repository;

    @Override
    @Transactional
    public User save(User o) {
        return UserEntityMapper.toDomain(repository.save(UserEntityMapper.fromDomain(o)));
    }

    @Override
    @Transactional
    public List<User> findAll() {
        return repository.findAll().stream().map(UserEntityMapper::toDomain).toList();
    }

    @Override
    @Transactional
    public Option<User> findById(UUID id) {
        return repository.findUserEntityById(id).map(UserEntityMapper::toDomain);
    }

    @Override
    @Transactional
    public Option<User> findUserByEmailAndPassword(String email, String password) {
        return repository.findUserEntityByEmailAndPassword(email, password).map(UserEntityMapper::toDomain);
    }

    @Override
    @Transactional
    public Option<User> findUserByPseudoAndPassword(String pseudo, String password) {
        return repository.findUserEntityByPseudoAndPassword(pseudo, password).map(UserEntityMapper::toDomain);
    }

}
