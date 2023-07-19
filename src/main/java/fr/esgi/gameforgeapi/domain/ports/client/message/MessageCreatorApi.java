package fr.esgi.gameforgeapi.domain.ports.client.message;

import fr.esgi.gameforgeapi.domain.functional.models.Message;

import java.util.UUID;

public interface MessageCreatorApi {

    Message create(UUID userToken, Message message);

}
