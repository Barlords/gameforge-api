package fr.esgi.gameforgeapi.server.adapters;

import fr.esgi.gameforgeapi.domain.functional.models.Lobby;
import fr.esgi.gameforgeapi.domain.ports.server.LobbyPersistenceSpi;
import fr.esgi.gameforgeapi.server.mappers.LobbyEntityMapper;
import fr.esgi.gameforgeapi.server.repositories.LobbyRepository;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LobbyDatabaseAdapter implements LobbyPersistenceSpi {

    private final LobbyRepository repository;

    @Override
    @Transactional
    public Lobby save(Lobby o) {
        return LobbyEntityMapper.toDomain(repository.save(LobbyEntityMapper.fromDomain(o)));
    }

    @Override
    @Transactional
    public List<Lobby> findAll() {
        return repository.findAll().stream().map(LobbyEntityMapper::toDomain).toList();
    }

    @Override
    @Transactional
    public Option<Lobby> findById(UUID id) {
        return repository.findLobbyEntityById(id).map(LobbyEntityMapper::toDomain);
    }


}
