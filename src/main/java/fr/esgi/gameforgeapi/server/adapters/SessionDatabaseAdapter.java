package fr.esgi.gameforgeapi.server.adapters;

import fr.esgi.gameforgeapi.domain.functional.models.Session;
import fr.esgi.gameforgeapi.domain.ports.server.SessionPersistenceSpi;
import fr.esgi.gameforgeapi.server.mappers.SessionEntityMapper;
import fr.esgi.gameforgeapi.server.repositories.SessionRepository;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SessionDatabaseAdapter implements SessionPersistenceSpi {

    private final SessionRepository repository;

    @Override
    @Transactional
    public Session save(Session o) {
        return SessionEntityMapper.toDomain(repository.save(SessionEntityMapper.fromDomain(o)));
    }

    @Override
    @Transactional
    public List<Session> findAll() {
        return repository.findAll().stream().map(SessionEntityMapper::toDomain).toList();
    }

    @Override
    @Transactional
    public Option<Session> findById(UUID id) {
        //return repository.findSessionEntityById(id).map(SessionEntityMapper::toDomain);
        return null;
    }


}
