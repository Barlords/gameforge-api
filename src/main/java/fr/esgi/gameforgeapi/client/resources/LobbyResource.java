package fr.esgi.gameforgeapi.client.resources;

import fr.esgi.gameforgeapi.client.dto.lobby.LobbyCreationRequest;
import fr.esgi.gameforgeapi.client.dto.lobby.LobbyDto;
import fr.esgi.gameforgeapi.client.mappers.LobbyDtoMapper;
import fr.esgi.gameforgeapi.client.validator.UuidValidator;
import fr.esgi.gameforgeapi.domain.functional.exceptions.ResourceNotFoundException;
import fr.esgi.gameforgeapi.domain.ports.client.lobby.LobbyCreatorApi;
import fr.esgi.gameforgeapi.domain.ports.client.lobby.LobbyFinderApi;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/lobbies")
public class LobbyResource {

    private final LobbyCreatorApi lobbyCreatorApi;

    private final LobbyFinderApi lobbyFinderApi;

    @GetMapping
    @ResponseStatus(OK)
    public List<LobbyDto> getLobbies() {
        return lobbyFinderApi.findAll()
                .stream()
                .map(LobbyDtoMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public LobbyDto getLobbyById(@PathVariable String id) {
        return lobbyFinderApi.findById(UuidValidator.validate(id))
                .map(LobbyDtoMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Le jeu : " + id + " est introuvable"));
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public LobbyDto createLobby(@Valid @RequestBody LobbyCreationRequest request) {
        return LobbyDtoMapper.toDto(
                lobbyCreatorApi.create(
                        LobbyDtoMapper.creationRequestToDomain(request)
                )
        );
    }
}
