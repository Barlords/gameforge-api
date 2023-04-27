package fr.esgi.gameforgeapi.server.adapters;

import fr.esgi.gameforgeapi.domain.functional.models.User;
import fr.esgi.gameforgeapi.domain.ports.server.UserPersistenceSpi;
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
        val entity = UserEntityMapper.fromDomain(o);
        return UserEntityMapper.toDomain(repository.save(entity));
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
}
