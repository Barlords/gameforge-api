package fr.esgi.gameforgeapi.server.adapters;

import fr.esgi.gameforgeapi.domain.functional.models.Action;
import fr.esgi.gameforgeapi.domain.functional.models.Channel;
import fr.esgi.gameforgeapi.domain.functional.models.Lobby;
import fr.esgi.gameforgeapi.domain.ports.server.ActionPersistenceSpi;
import fr.esgi.gameforgeapi.domain.ports.server.ChannelPersistenceSpi;
import fr.esgi.gameforgeapi.server.entities.ChannelEntity;
import fr.esgi.gameforgeapi.server.entities.LobbyEntity;
import fr.esgi.gameforgeapi.server.mappers.ActionEntityMapper;
import fr.esgi.gameforgeapi.server.mappers.ChannelEntityMapper;
import fr.esgi.gameforgeapi.server.mappers.LobbyEntityMapper;
import fr.esgi.gameforgeapi.server.repositories.ActionRepository;
import fr.esgi.gameforgeapi.server.repositories.ChannelRepository;
import fr.esgi.gameforgeapi.server.repositories.dao.IGenericDao;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChannelDatabaseAdapter implements ChannelPersistenceSpi {

    private IGenericDao<ChannelEntity> dao;

    @Override
    @Transactional
    public Channel save(Channel o) {
        return ChannelEntityMapper.toDomain(dao.save(ChannelEntityMapper.fromDomain(o)));
    }

    @Override
    @Transactional
    public List<Channel> findAll() {
        return dao.findAll().stream().map(ChannelEntityMapper::toDomain).toList();
    }

    @Override
    @Transactional
    public Option<Channel> findById(UUID id) {
        return Option.of(ChannelEntityMapper.toDomain(dao.findOne(id)));
    }
}
