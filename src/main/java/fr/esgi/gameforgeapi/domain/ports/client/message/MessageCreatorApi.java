package fr.esgi.gameforgeapi.domain.ports.client.message;

import fr.esgi.gameforgeapi.domain.functional.models.Message;

public interface MessageCreatorApi {

    Message create(Message user);

}
