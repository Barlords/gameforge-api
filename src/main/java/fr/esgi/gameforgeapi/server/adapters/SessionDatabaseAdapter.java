package fr.esgi.gameforgeapi.server.adapters;

import fr.esgi.gameforgeapi.domain.functional.models.Lobby;
import fr.esgi.gameforgeapi.domain.functional.models.Session;
import fr.esgi.gameforgeapi.domain.functional.models.User;
import fr.esgi.gameforgeapi.domain.ports.server.SessionPersistenceSpi;
import fr.esgi.gameforgeapi.server.entities.LobbyEntity;
import fr.esgi.gameforgeapi.server.entities.SessionEntity;
import fr.esgi.gameforgeapi.server.mappers.LobbyEntityMapper;
import fr.esgi.gameforgeapi.server.mappers.SessionEntityMapper;
import fr.esgi.gameforgeapi.server.mappers.UserEntityMapper;
import fr.esgi.gameforgeapi.server.repositories.LobbyRepository;
import fr.esgi.gameforgeapi.server.repositories.SessionRepository;
import fr.esgi.gameforgeapi.server.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SessionDatabaseAdapter implements SessionPersistenceSpi {

    private final SessionRepository sessionRepository;

    private final UserRepository userRepository;

    private final LobbyRepository lobbyRepository;

    @Override
    @Transactional
    public Session save(Session o) {
        return SessionEntityMapper.toDomain(sessionRepository.save(SessionEntityMapper.fromDomain(o)));
    }

    @Override
    @Transactional
    public List<Session> findAll() {
        return sessionRepository.findAll().stream().map(SessionEntityMapper::toDomain).toList();
    }

    @Override
    @Transactional
    public Optional<Session> findById(UUID id) {
        return sessionRepository.findSessionEntityById(id).map(SessionEntityMapper::toDomain);
    }

    @Override
    @Transactional
    public Optional<Session> findLastByUserIdAndQuitTimeIsNull(UUID userId) {
        Optional<SessionEntity> sessionEntityOptional = sessionRepository.findLastByUserIdAndQuitTimeIsNull(userId);

        return sessionEntityOptional.map(SessionEntityMapper::toDomain);
    }

    @Override
    @Transactional
    public void closeAllUserSessions(LocalDate quitDate, UUID userId) {
        sessionRepository.closeAllUserSessions(quitDate,userId);
    }

    @Override
    public void deleteById(UUID id) {
        sessionRepository.deleteById(id);
    }

    @Override
    @Transactional
    public List<Session> findByLobbyId(UUID id) {
        return sessionRepository.findSessionEntityByLobbyId(id).stream().map(SessionEntityMapper::toDomain).toList();
    }

    @Override
    @Transactional
    public Lobby closeAllCurrentSessionIfNecessary(String token) throws UserPrincipalNotFoundException {
        User u = UserEntityMapper.toDomain(userRepository.findByToken(UUID.fromString(token))
                .orElseThrow(() -> new UserPrincipalNotFoundException("")));
        if(u != null) {
            Optional<SessionEntity> se =  sessionRepository.findLastByUserIdAndQuitTimeIsNull(u.getId());
            if(se.isPresent()) {
                Session s = SessionEntityMapper.toDomain(se.get());
                s = s.withQuitDate(LocalDate.now());
                sessionRepository.saveAndFlush(SessionEntityMapper.fromDomain(s));
                System.out.println("Closed session " + s.getId() + " for User: " + u.getId());

                Optional<LobbyEntity> l = lobbyRepository.findLobbyEntityById(s.getLobbyId());
                return l.map(LobbyEntityMapper::toDomain).orElse(null);
            }
        }
        return null;
    }

}
