package fr.esgi.gameforgeapi.server.adapters;

import fr.esgi.gameforgeapi.domain.functional.models.User;
import fr.esgi.gameforgeapi.domain.ports.server.UserPersistenceSpi;
import fr.esgi.gameforgeapi.server.mappers.UserEntityMapper;
import fr.esgi.gameforgeapi.server.repositories.UserRepository;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
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
    public Optional<User> findById(UUID id) {
        return repository.findById(id).map(UserEntityMapper::toDomain);
    }

    @Override
    @Transactional
    public Optional<User> findByToken(UUID token) {
        return repository.findByToken(token);
    }

    @Override
    @Transactional
    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    @Transactional
    public Optional<User> findByPseudo(String pseudo) {
        return repository.findByPseudo(pseudo);
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
