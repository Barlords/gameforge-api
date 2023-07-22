package fr.esgi.gameforgeapi.domain.functional.services.session;

import fr.esgi.gameforgeapi.domain.functional.exceptions.UserHasAlreadyASessionException;
import fr.esgi.gameforgeapi.domain.functional.models.Session;
import fr.esgi.gameforgeapi.domain.ports.client.session.SessionCreatorApi;
import fr.esgi.gameforgeapi.domain.ports.server.SessionPersistenceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class SessionCreatorService implements SessionCreatorApi {

    private final SessionPersistenceSpi spi;

    private final SessionFinderService sessionFinderService;


    @Override
    public Session create(Session session) {

        Optional<Session> userHasAlreadyASession = sessionFinderService.findLastByUserId(session.getUserId());

        if(userHasAlreadyASession.isPresent()) {
            throw new UserHasAlreadyASessionException("l'utilisateur a déja une session active");
        }

        return spi.save(session);
    }




}
