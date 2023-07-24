package fr.esgi.gameforgeapi.domain.functional.services.session;

import fr.esgi.gameforgeapi.domain.functional.exceptions.NotFoundUserException;
import fr.esgi.gameforgeapi.domain.functional.models.Session;
import fr.esgi.gameforgeapi.domain.functional.models.User;
import fr.esgi.gameforgeapi.domain.ports.client.session.SessionUpdaterApi;
import fr.esgi.gameforgeapi.domain.ports.server.SessionPersistenceSpi;
import fr.esgi.gameforgeapi.domain.ports.server.UserPersistenceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class SessionUpdaterService implements SessionUpdaterApi {

    private final SessionPersistenceSpi spi;

    private final UserPersistenceSpi userPersistenceSpi;

    @Override
    public Session update(Session session) {
        return spi.save(session);
    }

    @Override
    public void closeAllUserSessions(UUID userToken) {
        User user = userPersistenceSpi.findByToken(userToken)
                .orElseThrow(() -> new NotFoundUserException("Aucun utilisateur ne correspond au token fournit"));
        spi.closeAllUserSessions(LocalDate.now(),user.getId());
    }

}
