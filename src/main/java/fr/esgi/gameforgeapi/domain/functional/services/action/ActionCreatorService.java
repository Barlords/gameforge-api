package fr.esgi.gameforgeapi.domain.functional.services.action;

import fr.esgi.gameforgeapi.domain.functional.models.Action;
import fr.esgi.gameforgeapi.domain.ports.client.action.ActionCreatorApi;
import fr.esgi.gameforgeapi.domain.ports.server.ActionPersistenceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class ActionCreatorService implements ActionCreatorApi {

    private final ActionPersistenceSpi spi;
    @Override
    public Action create(Action action) {
        return spi.save(action);
    }


}
