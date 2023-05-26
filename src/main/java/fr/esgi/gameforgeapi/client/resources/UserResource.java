package fr.esgi.gameforgeapi.client.resources;

import fr.esgi.gameforgeapi.client.dto.UserCreationRequest;
import fr.esgi.gameforgeapi.client.dto.UserDto;
import fr.esgi.gameforgeapi.client.mappers.UserDtoMapper;
import fr.esgi.gameforgeapi.domain.ports.client.UserCreatorApi;
import fr.esgi.gameforgeapi.domain.ports.client.UserFinderApi;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/user")
//@CrossOrigin("*")
public class UserResource  extends GenericResource {

    private final UserCreatorApi userCreatorApi;

    private final UserFinderApi userFinderApi;

    @PostMapping(path = "/create")
    public UserDto createUser(@RequestBody UserCreationRequest request) {
        return UserDtoMapper.toDto(userCreatorApi.create(UserDtoMapper.userCreationRequest(request)));
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
