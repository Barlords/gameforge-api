package fr.esgi.gameforgeapi.client.resources;


import fr.esgi.gameforgeapi.client.dto.message.MessageCreationRequest;
import fr.esgi.gameforgeapi.client.dto.message.MessageDto;
import fr.esgi.gameforgeapi.client.mappers.MessageDtoMapper;
import fr.esgi.gameforgeapi.client.validator.UuidValidator;
import fr.esgi.gameforgeapi.domain.ports.client.message.MessageCreatorApi;
import fr.esgi.gameforgeapi.domain.ports.client.message.MessageFinderApi;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/messages")
public class MessageResource {

    private final MessageCreatorApi messageCreatorApi;

    private final MessageFinderApi messageFinderApi;

    @GetMapping("/{user_token}/{receiver_id}")
    @ResponseStatus(OK)
    public List<MessageDto> getMessages(@PathVariable String user_token, @PathVariable String receiver_id) {
        return messageFinderApi.findDiscussionWith(UuidValidator.validate(user_token), UuidValidator.validate(receiver_id))
                .stream()
                .map(MessageDtoMapper::toDto)
                .toList();
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public void sendMessage(@RequestBody MessageCreationRequest request) {
        messageCreatorApi.create(UuidValidator.validate(request.userToken()), MessageDtoMapper.creationRequestToDomain(request));
    }

}
