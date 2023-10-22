package fr.esgi.gameforgeapi.server.adapters;

import fr.esgi.gameforgeapi.domain.functional.models.Session;
import fr.esgi.gameforgeapi.domain.functional.models.User;
import fr.esgi.gameforgeapi.domain.ports.server.SessionPersistenceSpi;
import fr.esgi.gameforgeapi.server.entities.SessionEntity;
import fr.esgi.gameforgeapi.server.mappers.SessionEntityMapper;
import fr.esgi.gameforgeapi.server.repositories.SessionRepository;
import fr.esgi.gameforgeapi.server.repositories.dao.ISessionDao;
import fr.esgi.gameforgeapi.server.repositories.dao.IUserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SessionDatabaseAdapter implements SessionPersistenceSpi {

    private final SessionRepository repository;

    @Autowired
    private ISessionDao sessionDao;

    @Autowired
    private IUserDao userDao;

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
    public Optional<Session> findById(UUID id) {
        return repository.findSessionEntityById(id).map(SessionEntityMapper::toDomain);
    }

    @Override
    @Transactional
    public Optional<Session> findLastByUserIdAndQuitTimeIsNull(UUID userId) {
        Optional<SessionEntity> sessionEntityOptional = repository.findLastByUserIdAndQuitTimeIsNull(userId);

        return sessionEntityOptional.map(SessionEntityMapper::toDomain);
    }

    @Override
    @Transactional
    public void closeAllUserSessions(LocalDate quitDate, UUID userId) {
        repository.closeAllUserSessions(quitDate,userId);
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public Optional<Session> findByLobbyId(UUID id) {
        return repository.findSessionEntityByLobbyId(id).map(SessionEntityMapper::toDomain);
    }

    @Override
    @Transactional
    public void closeAllCurrentSessionIfNecessary(String token) {
        User u = userDao.findUserByToken(UUID.fromString(token));
        if(u != null) {
            Optional<SessionEntity> se =  repository.findLastByUserIdAndQuitTimeIsNull(u.getId());
            if(se.isPresent()) {
                Session s = SessionEntityMapper.toDomain(se.get());
                Session s_ = s.withQuitDate(LocalDate.now());
                sessionDao.save(SessionEntityMapper.fromDomain(s_));
                System.out.println("Closed session " + s_.getId() + " for User: " + u.getId());
            }
        }
    }

}
