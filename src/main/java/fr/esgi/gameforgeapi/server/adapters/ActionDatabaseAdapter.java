package fr.esgi.gameforgeapi.server.adapters;

import fr.esgi.gameforgeapi.domain.functional.models.Action;
import fr.esgi.gameforgeapi.domain.functional.models.User;
import fr.esgi.gameforgeapi.domain.ports.server.ActionPersistenceSpi;
import fr.esgi.gameforgeapi.domain.ports.server.UserPersistenceSpi;
import fr.esgi.gameforgeapi.server.mappers.ActionEntityMapper;
import fr.esgi.gameforgeapi.server.mappers.UserEntityMapper;
import fr.esgi.gameforgeapi.server.repositories.ActionRepository;
import fr.esgi.gameforgeapi.server.repositories.UserRepository;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ActionDatabaseAdapter implements ActionPersistenceSpi {

    private final ActionRepository repository;

    @Override
    @Transactional
    public Action save(Action o) {
        return ActionEntityMapper.toDomain(repository.save(ActionEntityMapper.fromDomain(o)));
    }

    @Override
    @Transactional
    public List<Action> findAll() {
        return repository.findAll().stream().map(ActionEntityMapper::toDomain).toList();
    }

    @Override
    @Transactional
    public Option<Action> findById(UUID id) {
        return repository.findActionEntityById(id).map(ActionEntityMapper::toDomain);
    }


}
