package fr.esgi.gameforgeapi.client.resources;

import fr.esgi.gameforgeapi.client.dto.UserCreationRequest;
import fr.esgi.gameforgeapi.client.dto.UserDto;
import fr.esgi.gameforgeapi.client.dto.UserLogRequest;
import fr.esgi.gameforgeapi.client.mappers.UserDtoMapper;
import fr.esgi.gameforgeapi.domain.ports.client.UserCreatorApi;
import fr.esgi.gameforgeapi.domain.ports.client.UserFinderApi;
import fr.esgi.gameforgeapi.domain.ports.client.UserLoggerApi;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/user")
public class UserResource  extends GenericResource {

    private final UserCreatorApi userCreatorApi;

    private final UserFinderApi userFinderApi;

    private final UserLoggerApi userLoggerApi;

    @PostMapping(path = "/create")
    public UserDto createUser(@RequestBody UserCreationRequest request) {
        return UserDtoMapper.toDto(userCreatorApi.create(UserDtoMapper.userCreationRequest(request)));
    }

    @GetMapping(path = "/log")
    public Option<UserDto> login(@RequestBody UserLogRequest request) {
        return userLoggerApi.login(request.login(), request.password()).map(UserDtoMapper::toDto);
    }

    @GetMapping()
    public List<UserDto> findAllUser() {
        return userFinderApi
                .findAll()
                .stream()
                .map(UserDtoMapper::toDto)
                .toList();
    }

    @GetMapping(path = "/{userId}")
    public Option<UserDto> findUserById(@PathVariable("userId") UUID id) {
        return userFinderApi.findById(id).map(UserDtoMapper::toDto);
    }



}
