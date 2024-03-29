package fr.esgi.gameforgeapi.client.resources;

import fr.esgi.gameforgeapi.client.dto.session.SessionCreationRequest;
import fr.esgi.gameforgeapi.client.dto.session.SessionDto;
import fr.esgi.gameforgeapi.client.mappers.SessionDtoMapper;
import fr.esgi.gameforgeapi.client.validator.UuidValidator;
import fr.esgi.gameforgeapi.domain.functional.exceptions.ResourceNotFoundException;
import fr.esgi.gameforgeapi.domain.functional.models.Session;
import fr.esgi.gameforgeapi.domain.ports.client.session.SessionCreatorApi;
import fr.esgi.gameforgeapi.domain.ports.client.session.SessionFinderApi;
import fr.esgi.gameforgeapi.domain.ports.client.session.SessionUpdaterApi;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/sessions")
public class SessionResource {

    private final SessionCreatorApi sessionCreatorApi;

    private final SessionFinderApi sessionFinderApi;

    private final SessionUpdaterApi sessionUpdaterApi;


    @GetMapping
    @ResponseStatus(OK)
    public List<SessionDto> getSessions() {
        return sessionFinderApi.findAll()
                .stream()
                .map(SessionDtoMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public SessionDto getSessionById(@PathVariable String id) {
        return sessionFinderApi.findById(UuidValidator.validate(id))
                .map(SessionDtoMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("La session : " + id + " est introuvable"));
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public SessionDto createSession(@Valid @RequestBody SessionCreationRequest request) {
        return SessionDtoMapper.toDto(
                sessionCreatorApi.create(
                        SessionDtoMapper.creationRequestToDomain(request)
                )
        );
    }

    @GetMapping("/user/{id}")
    @ResponseStatus(OK)
    public SessionDto getLastSession(@Valid @PathVariable UUID id) {
        return sessionFinderApi.findLastByUserIdAndQuitTimeIsNull(id)
                .map(SessionDtoMapper::toDto).orElse(null);
    }

    @PatchMapping("/{user_token}")
    @ResponseStatus(OK)
    public void updateSession(@PathVariable String user_token) {
        sessionUpdaterApi.closeAllUserSessions(UUID.fromString(user_token));
    }

    @GetMapping("/lobby/{id}")
    @ResponseStatus(OK)
    public SessionDto getSessionByLobbyId(@PathVariable String id) {
        return sessionFinderApi.findById(UuidValidator.validate(id))
                .map(SessionDtoMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("La session : " + id + " est introuvable"));
    }
}
