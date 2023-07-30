package fr.esgi.gameforgeapi.domain.ports.client.user;

import fr.esgi.gameforgeapi.domain.functional.models.User;

public interface UserUpdaterApi {

    User update(User user);

}
