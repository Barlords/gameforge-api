package fr.esgi.gameforgeapi.server.adapters;

import fr.esgi.gameforgeapi.domain.functional.models.Rank;
import fr.esgi.gameforgeapi.domain.ports.server.RankPersistenceSpi;
import fr.esgi.gameforgeapi.server.entities.RankEntity;
import fr.esgi.gameforgeapi.server.mappers.RankEntityMapper;
import fr.esgi.gameforgeapi.server.repositories.dao.IGenericDao;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RankDatabaseAdapter implements RankPersistenceSpi {

    private final IGenericDao<RankEntity> dao;

    @Override
    @Transactional
    public Rank save(Rank o) {
        return RankEntityMapper.toDomain(dao.save(RankEntityMapper.fromDomain(o)));
    }

    @Override
    @Transactional
    public List<Rank> findAll() {
        return dao.findAll().stream().map(RankEntityMapper::toDomain).toList();
    }

    @Override
    @Transactional
    public Option<Rank> findById(UUID id) {
        return Option.of(RankEntityMapper.toDomain(dao.findOne(id)));
    }


}
