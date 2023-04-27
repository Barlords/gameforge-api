package fr.esgi.gameforgeapi.domain.ports.client;

import fr.esgi.gameforgeapi.domain.functional.models.User;
import io.vavr.control.Option;

import java.util.List;
import java.util.UUID;

public interface UserFinderApi {

    List<User> findAll();

    Option<User> findById(UUID id);

}
