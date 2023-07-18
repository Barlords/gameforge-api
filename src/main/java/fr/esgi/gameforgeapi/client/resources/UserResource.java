package fr.esgi.gameforgeapi.client.resources;

import fr.esgi.gameforgeapi.client.dto.user.UserCreationRequest;
import fr.esgi.gameforgeapi.client.dto.user.UserDto;
import fr.esgi.gameforgeapi.client.dto.user.UserLogRequest;
import fr.esgi.gameforgeapi.domain.functional.exceptions.NotFoundUserException;
import fr.esgi.gameforgeapi.client.mappers.UserDtoMapper;
import fr.esgi.gameforgeapi.domain.functional.services.user.UserModifierService;
import fr.esgi.gameforgeapi.domain.ports.client.user.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/users")
public class UserResource {

    private final UserCreatorApi userCreatorApi;

    private final UserUpdaterApi userUpdaterApi;

    private final UserFinderApi userFinderApi;

    private final UserLoggerApi userLoggerApi;

    private final UserModifierService userModifierService;

    private final UserDeleterApi userDeleterApi;

    @GetMapping
    @ResponseStatus(OK)
    public List<UserDto> getUsers() {
        return userFinderApi.findAll()
                .stream()
                .map(UserDtoMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public UserDto getUserById(@PathVariable String id) {
        return userFinderApi.findById(UUID.fromString(id))
                .map(UserDtoMapper::toDto)
                .orElseThrow(() -> new NotFoundUserException("L'utilisateur " + id + " est introuvable"));
    }

    @GetMapping("/{token}")
    @ResponseStatus(OK)
    public UserDto getUserByToken(@PathVariable String token) {
        return userFinderApi.findByToken(UUID.fromString(token))
                .map(UserDtoMapper::toDto)
                .orElseThrow(() -> new NotFoundUserException("L'utilisateur avec le token" + token + " est introuvable"));
    }

    @GetMapping("/{email}")
    @ResponseStatus(OK)
    public UserDto getUserByEmail(@PathVariable String email) {
        return userFinderApi.findByEmail(email)
                .map(UserDtoMapper::toDto)
                .orElseThrow(() -> new NotFoundUserException("L'utilisateur avec l'email \"" + email + "\" est introuvable"));
    }

    @GetMapping("/{pseudo}")
    @ResponseStatus(OK)
    public UserDto getUserByPseudo(@PathVariable String pseudo) {
        return userFinderApi.findByPseudo(pseudo)
                .map(UserDtoMapper::toDto)
                .orElseThrow(() -> new NotFoundUserException("L'utilisateur avec le pseudo \"" + pseudo + "\" est introuvable"));
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public UserDto createUser(@Valid @RequestBody UserCreationRequest request) {
        return UserDtoMapper.toDto(
                userCreatorApi.create(
                        UserDtoMapper.creationRequestToDomain(request)
                )
        );
    }

    @PutMapping("/{id}")
    @ResponseStatus(OK)
    public UserDto createOrUpdateUser(@PathVariable String id, @Valid @RequestBody UserCreationRequest request) {
        return UserDtoMapper.toDto(
                userCreatorApi.create(
                        userModifierService.setId(UserDtoMapper.creationRequestToDomain(request), id)
                )
        );
    }

    @PatchMapping("/{token}")
    @ResponseStatus(OK)
    public UserDto patchUser(@PathVariable String token, @Valid @RequestBody UserCreationRequest request) {
        return UserDtoMapper.toDto(
                userUpdaterApi.update(
                        userModifierService.setToken(UserDtoMapper.creationRequestToDomain(request), token)
                )
        );
    }

    @DeleteMapping("/{token}")
    @ResponseStatus(NO_CONTENT)
    public void deleteUserByToken(@PathVariable String token) {
        userDeleterApi.deleteByToken(UUID.fromString(token));
    }


    @GetMapping(path = "/log")
    public UserDto login(@RequestBody UserLogRequest request) {
        return userLoggerApi.login(request.login(), request.password())
                .map(UserDtoMapper::toDto)
                .orElseThrow(() -> new NotFoundUserException("Aucun utilisateur ne correspond au login fournit"));
    }

}
