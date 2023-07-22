package fr.esgi.gameforgeapi.client.resources;

import fr.esgi.gameforgeapi.client.dto.game.GameCreationRequest;
import fr.esgi.gameforgeapi.client.dto.game.GameDto;
import fr.esgi.gameforgeapi.client.mappers.GameDtoMapper;
import fr.esgi.gameforgeapi.domain.functional.exceptions.NotFoundUserException;
import fr.esgi.gameforgeapi.domain.ports.client.game.GameCreatorApi;
import fr.esgi.gameforgeapi.domain.ports.client.game.GameFinderApi;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/games")
public class GameResource {

    private final GameCreatorApi gameCreatorApi;

    private final GameFinderApi gameFinderApi;

    @GetMapping
    @ResponseStatus(OK)
    public List<GameDto> getGames() {
        return gameFinderApi.findAll()
                .stream()
                .map(GameDtoMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public GameDto getGameById(@PathVariable String id) {
        return gameFinderApi.findById(UUID.fromString(id))
                .map(GameDtoMapper::toDto)
                .orElseThrow(() -> new NotFoundUserException("Le jeu : " + id + " est introuvable"));
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public GameDto createGame(@Valid @RequestBody GameCreationRequest request) {
        return GameDtoMapper.toDto(
                gameCreatorApi.create(
                        GameDtoMapper.creationRequestToDomain(request)
                )
        );
    }
}
