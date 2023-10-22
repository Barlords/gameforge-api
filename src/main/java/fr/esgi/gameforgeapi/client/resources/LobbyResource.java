package fr.esgi.gameforgeapi.client.resources;

import fr.esgi.gameforgeapi.client.dto.lobby.LobbyCreationRequest;
import fr.esgi.gameforgeapi.client.dto.lobby.LobbyDto;
import fr.esgi.gameforgeapi.client.mappers.LobbyDtoMapper;
import fr.esgi.gameforgeapi.client.validator.UuidValidator;
import fr.esgi.gameforgeapi.domain.functional.exceptions.ResourceNotFoundException;
import fr.esgi.gameforgeapi.domain.ports.client.lobby.LobbyCreatorApi;
import fr.esgi.gameforgeapi.domain.ports.client.lobby.LobbyDeleterApi;
import fr.esgi.gameforgeapi.domain.ports.client.lobby.LobbyFinderApi;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/lobbies")
public class LobbyResource {

    private final LobbyCreatorApi lobbyCreatorApi;

    private final LobbyFinderApi lobbyFinderApi;

    private final LobbyDeleterApi lobbyDeleterApi;

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

    @GetMapping("/getByGame/{game_id}")
    @ResponseStatus(OK)
    public List<LobbyDto> getLobbiesByGame(@PathVariable("game_id") String gameId) {
        return lobbyFinderApi.findByGameId(UuidValidator.validate(gameId))
                .stream()
                .map(LobbyDtoMapper::toDto)
                .toList();
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public LobbyDto createLobby(@Valid @RequestBody LobbyCreationRequest request) {

        return LobbyDtoMapper.toDto(
                lobbyCreatorApi.create(
                        UuidValidator.validate(request.userToken()),
                        LobbyDtoMapper.creationRequestToDomain(request)
                )
        );
    }

    @DeleteMapping("/{user_token}/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteLobby(@PathVariable("user_token") String userToken, @PathVariable("id") String id) {
        lobbyDeleterApi.delete(
                UuidValidator.validate(userToken),
                UuidValidator.validate(id)
        );
    }
}
