package fr.esgi.gameforgeapi.domain.functional.services.action;

import fr.esgi.gameforgeapi.domain.functional.models.Action;
import fr.esgi.gameforgeapi.domain.functional.models.User;
import fr.esgi.gameforgeapi.domain.functional.services.TokenControllerService;
import fr.esgi.gameforgeapi.domain.ports.client.action.ActionCreatorApi;
import fr.esgi.gameforgeapi.domain.ports.server.ActionPersistenceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class ActionCreatorService implements ActionCreatorApi {

    private final ActionPersistenceSpi spi;

    private final TokenControllerService tokenControllerService;

    @Override
    public Action create(Action action) {
        User user = tokenControllerService.getUser(action.getUserId());
        return spi.save(action.withUserId(user.getId()));
    }


}
