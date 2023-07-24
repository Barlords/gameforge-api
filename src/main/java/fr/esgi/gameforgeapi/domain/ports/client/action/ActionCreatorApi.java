package fr.esgi.gameforgeapi.domain.ports.client.action;

import fr.esgi.gameforgeapi.domain.functional.models.Action;

public interface ActionCreatorApi {

    Action create(Action action);
}
