package fr.esgi.gameforgeapi.domain.ports.client;

import fr.esgi.gameforgeapi.domain.functional.models.User;

public interface UserCreatorApi {

    User create(User user);

}
