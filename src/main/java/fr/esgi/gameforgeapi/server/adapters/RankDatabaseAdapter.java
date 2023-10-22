package fr.esgi.gameforgeapi.server.adapters;

import fr.esgi.gameforgeapi.domain.functional.models.Rank;
import fr.esgi.gameforgeapi.domain.ports.server.RankPersistenceSpi;
import fr.esgi.gameforgeapi.server.mappers.RankEntityMapper;
import fr.esgi.gameforgeapi.server.repositories.RankRepository;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RankDatabaseAdapter implements RankPersistenceSpi {

    private final RankRepository repository;

    @Override
    @Transactional
    public Rank save(Rank o) {
        return RankEntityMapper.toDomain(repository.save(RankEntityMapper.fromDomain(o)));
    }

    @Override
    @Transactional
    public List<Rank> findAll() {
        return repository.findAll().stream().map(RankEntityMapper::toDomain).toList();
    }

    @Override
    @Transactional
    public Optional<Rank> findById(UUID id) {
        return repository.findRankEntityById(id).map(RankEntityMapper::toDomain);
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }


    @Override
    @Transactional
    public List<Rank> findByGame(UUID gameId) {
        return repository.findRankEntitiesByGameId(gameId).stream().map(RankEntityMapper::toDomain).toList();
    }
}
