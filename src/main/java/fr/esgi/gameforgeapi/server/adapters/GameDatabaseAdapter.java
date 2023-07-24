package fr.esgi.gameforgeapi.server.adapters;

import fr.esgi.gameforgeapi.domain.functional.models.Game;
import fr.esgi.gameforgeapi.domain.ports.server.GamePersistenceSpi;
import fr.esgi.gameforgeapi.server.mappers.GameEntityMapper;
import fr.esgi.gameforgeapi.server.repositories.GameRepository;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GameDatabaseAdapter implements GamePersistenceSpi {

    private final GameRepository repository;

    @Override
    @Transactional
    public Game save(Game o) {
        return GameEntityMapper.toDomain(repository.save(GameEntityMapper.fromDomain(o)));
    }

    @Override
    @Transactional
    public List<Game> findAll() {
        return repository.findAll().stream().map(GameEntityMapper::toDomain).toList();
    }

    @Override
    @Transactional
    public Optional<Game> findById(UUID id) {
        return repository.findGameEntityById(id).map(GameEntityMapper::toDomain);
    }

    @Override
    @Transactional
    public Optional<Game> findByName(String name) {
        return repository.findGameEntityByName(name).map(GameEntityMapper::toDomain);
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

}
