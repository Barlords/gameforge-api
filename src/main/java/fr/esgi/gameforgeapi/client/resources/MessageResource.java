package fr.esgi.gameforgeapi.client.resources;


import fr.esgi.gameforgeapi.client.dto.message.MessageDto;
import fr.esgi.gameforgeapi.client.mappers.UserDtoMapper;
import fr.esgi.gameforgeapi.domain.ports.client.message.MessageCreatorApi;
import fr.esgi.gameforgeapi.domain.ports.client.message.MessageFinderApi;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/messages")
public class MessageResource {
/*
    private final MessageCreatorApi messageCreatorApi;

    private final MessageFinderApi messageFinderApi;
*/
}
