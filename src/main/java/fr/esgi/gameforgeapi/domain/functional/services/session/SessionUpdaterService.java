package fr.esgi.gameforgeapi.domain.functional.services.session;

import fr.esgi.gameforgeapi.domain.functional.exceptions.ResourceNotFoundException;
import fr.esgi.gameforgeapi.domain.functional.models.Lobby;
import fr.esgi.gameforgeapi.domain.functional.models.Session;
import fr.esgi.gameforgeapi.domain.functional.models.User;
import fr.esgi.gameforgeapi.domain.ports.client.session.SessionUpdaterApi;
import fr.esgi.gameforgeapi.domain.ports.server.SessionPersistenceSpi;
import fr.esgi.gameforgeapi.domain.ports.server.UserPersistenceSpi;
import fr.esgi.gameforgeapi.server.mappers.SessionEntityMapper;
import fr.esgi.gameforgeapi.server.repositories.dao.ILobbyDao;
import fr.esgi.gameforgeapi.server.repositories.dao.ISessionDao;
import fr.esgi.gameforgeapi.server.repositories.dao.IUserDao;
import fr.esgi.gameforgeapi.server.repositories.dao.impl.LobbyDao;
import fr.esgi.gameforgeapi.server.repositories.dao.impl.SessionDao;
import fr.esgi.gameforgeapi.server.repositories.dao.impl.UserDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class SessionUpdaterService implements SessionUpdaterApi {

    private final SessionPersistenceSpi spi;

    private final UserPersistenceSpi userPersistenceSpi;


    @Override
    @Transactional
    public Session update(Session session) {
        return spi.save(session);
    }

    @Override
    public void closeAllUserSessions(UUID userToken) {
        User user = userPersistenceSpi.findByToken(userToken)
                .orElseThrow(() -> new ResourceNotFoundException("Aucun utilisateur ne correspond au token fournit"));
        spi.closeAllUserSessions(LocalDate.now(),user.getId());
    }

    @Override
    @Transactional
    public Lobby closeCurrentSessionIfNecessary(String token) throws UserPrincipalNotFoundException {
        return spi.closeAllCurrentSessionIfNecessary(token);
    }
}
