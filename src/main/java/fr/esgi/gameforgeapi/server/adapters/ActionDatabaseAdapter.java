package fr.esgi.gameforgeapi.server.adapters;

import fr.esgi.gameforgeapi.domain.functional.models.Action;
import fr.esgi.gameforgeapi.domain.ports.server.ActionPersistenceSpi;
import fr.esgi.gameforgeapi.server.mappers.ActionEntityMapper;
import fr.esgi.gameforgeapi.server.repositories.ActionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
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
    public Optional<Action> findById(UUID id) {
        return repository.findActionEntityById(id).map(ActionEntityMapper::toDomain);
    }

    @Override
    @Transactional
    public List<Action> findByLobbyId(UUID lobbyId) {
        return repository.findActionEntityByLobbyId(lobbyId).stream().map(ActionEntityMapper::toDomain).toList();
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }


}
