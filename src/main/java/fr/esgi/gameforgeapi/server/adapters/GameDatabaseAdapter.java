package fr.esgi.gameforgeapi.server.adapters;

import fr.esgi.gameforgeapi.domain.functional.models.Game;
import fr.esgi.gameforgeapi.domain.functional.models.Lobby;
import fr.esgi.gameforgeapi.domain.ports.server.GamePersistenceSpi;
import fr.esgi.gameforgeapi.server.entities.GameEntity;
import fr.esgi.gameforgeapi.server.entities.LobbyEntity;
import fr.esgi.gameforgeapi.server.mappers.GameEntityMapper;
import fr.esgi.gameforgeapi.server.mappers.LobbyEntityMapper;
import fr.esgi.gameforgeapi.server.repositories.GameRepository;
import fr.esgi.gameforgeapi.server.repositories.dao.IGenericDao;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GameDatabaseAdapter implements GamePersistenceSpi {

    private IGenericDao<GameEntity> dao;

    @Override
    @Transactional
    public Game save(Game o) {
        return GameEntityMapper.toDomain(dao.save(GameEntityMapper.fromDomain(o)));
    }

    @Override
    @Transactional
    public List<Game> findAll() {
        return dao.findAll().stream().map(GameEntityMapper::toDomain).toList();
    }

    @Override
    @Transactional
    public Option<Game> findById(UUID id) {
        return Option.of(GameEntityMapper.toDomain(dao.findOne(id)));
    }


}
