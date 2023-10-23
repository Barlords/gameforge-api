package fr.esgi.gameforgeapi.client.resources;

import fr.esgi.gameforgeapi.client.dto.lobby.LobbyCreationRequest;
import fr.esgi.gameforgeapi.client.dto.lobby.LobbyDto;
import fr.esgi.gameforgeapi.client.mappers.LobbyDtoMapper;
import fr.esgi.gameforgeapi.client.validator.UuidValidator;
import fr.esgi.gameforgeapi.domain.functional.exceptions.ResourceNotFoundException;
import fr.esgi.gameforgeapi.domain.functional.models.Channel;
import fr.esgi.gameforgeapi.domain.functional.models.Lobby;
import fr.esgi.gameforgeapi.domain.ports.client.channel.ChannelCreatorApi;
import fr.esgi.gameforgeapi.domain.ports.client.channel.ChannelFinderApi;
import fr.esgi.gameforgeapi.domain.ports.client.lobby.LobbyCreatorApi;
import fr.esgi.gameforgeapi.domain.ports.client.lobby.LobbyDeleterApi;
import fr.esgi.gameforgeapi.domain.ports.client.lobby.LobbyFinderApi;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    private final ChannelCreatorApi channelCreatorApi;

    private final ChannelFinderApi channelFinderApi;


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
        LobbyDto l = LobbyDtoMapper.toDto(
                lobbyCreatorApi.create(
                        UuidValidator.validate(request.userToken()),
                        LobbyDtoMapper.creationRequestToDomain(request)
                )
        );
        System.out.println(l);
        Channel c = Channel.builder().lobbyId(l.id()).creatorId(l.creatorId()).creationDate(LocalDate.now()).build();
                System.out.println(c);
        channelCreatorApi.create(c);
        return l;
    }

    @DeleteMapping("/{user_token}/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteLobby(@PathVariable("user_token") String userToken, @PathVariable("id") String id) {
        lobbyDeleterApi.delete(
                UuidValidator.validate(userToken),
                UuidValidator.validate(id)
        );
    }

    @GetMapping("/{lobby_id}/channel")
    @ResponseStatus(OK)
    public Channel getLobbyChannel(@PathVariable("lobby_id") String lobbyId) {
        return channelFinderApi.getChannelByLobbyId(UuidValidator.validate(lobbyId));
    }
}
