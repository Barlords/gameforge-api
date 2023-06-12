package fr.esgi.gameforgeapi.server.adapters;

import fr.esgi.gameforgeapi.domain.functional.models.Action;
import fr.esgi.gameforgeapi.domain.functional.models.Channel;
import fr.esgi.gameforgeapi.domain.ports.server.ActionPersistenceSpi;
import fr.esgi.gameforgeapi.domain.ports.server.ChannelPersistenceSpi;
import fr.esgi.gameforgeapi.server.mappers.ActionEntityMapper;
import fr.esgi.gameforgeapi.server.mappers.ChannelEntityMapper;
import fr.esgi.gameforgeapi.server.repositories.ActionRepository;
import fr.esgi.gameforgeapi.server.repositories.ChannelRepository;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChannelDatabaseAdapter implements ChannelPersistenceSpi {

    private final ChannelRepository repository;

    @Override
    @Transactional
    public Channel save(Channel o) {
        return ChannelEntityMapper.toDomain(repository.save(ChannelEntityMapper.fromDomain(o)));
    }

    @Override
    @Transactional
    public List<Channel> findAll() {
        return repository.findAll().stream().map(ChannelEntityMapper::toDomain).toList();
    }

    @Override
    @Transactional
    public Option<Channel> findById(UUID id) {
        return repository.findChannelEntityById(id).map(ChannelEntityMapper::toDomain);
    }


}
