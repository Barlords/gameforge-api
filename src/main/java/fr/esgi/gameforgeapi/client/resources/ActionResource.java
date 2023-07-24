package fr.esgi.gameforgeapi.client.resources;

import fr.esgi.gameforgeapi.client.dto.action.ActionCreationRequest;
import fr.esgi.gameforgeapi.client.dto.action.ActionDto;
import fr.esgi.gameforgeapi.client.mappers.ActionDtoMapper;
import fr.esgi.gameforgeapi.client.validator.UuidValidator;
import fr.esgi.gameforgeapi.domain.functional.exceptions.ResourceNotFoundException;
import fr.esgi.gameforgeapi.domain.ports.client.action.ActionCreatorApi;
import fr.esgi.gameforgeapi.domain.ports.client.action.ActionFinderApi;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/actions")
public class ActionResource {

    private final ActionCreatorApi actionCreatorApi;

    private final ActionFinderApi actionFinderApi;

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public ActionDto getActionById(@PathVariable String id) {
        return actionFinderApi.findById(UuidValidator.validate(id))
                .map(ActionDtoMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("L'action " + id + " est introuvable"));
    }
    @GetMapping("/{lobby_id}")
    @ResponseStatus(OK)
    public List<ActionDto> getUserByPseudo(@PathVariable String lobby_id) {
        return actionFinderApi.findByLobbyId(UuidValidator.validate(lobby_id))
                .stream()
                .map(ActionDtoMapper::toDto)
                .toList();
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public ActionDto createUser(@Valid @RequestBody ActionCreationRequest request) {
        return ActionDtoMapper.toDto(
                actionCreatorApi.create(
                        ActionDtoMapper.creationRequestToDomain(request)
                )
        );
    }
}
