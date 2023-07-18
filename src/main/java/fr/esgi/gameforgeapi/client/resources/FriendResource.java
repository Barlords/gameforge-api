package fr.esgi.gameforgeapi.client.resources;

import fr.esgi.gameforgeapi.client.dto.friend.FriendCreationRequest;
import fr.esgi.gameforgeapi.client.dto.user.UserDto;
import fr.esgi.gameforgeapi.client.dto.friend.FriendDto;
import fr.esgi.gameforgeapi.client.mappers.FriendDtoMapper;
import fr.esgi.gameforgeapi.client.mappers.UserDtoMapper;
import fr.esgi.gameforgeapi.client.validator.UuidValidator;
import fr.esgi.gameforgeapi.domain.functional.exceptions.UuidNotValidException;
import fr.esgi.gameforgeapi.domain.ports.client.friend.FriendCreatorApi;
import fr.esgi.gameforgeapi.domain.ports.client.friend.FriendFinderApi;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/friends")
public class FriendResource {

    private final FriendCreatorApi friendCreatorApi;

    private final FriendFinderApi friendFinderApi;

    //private final FriendDeleterApi friendDeleterApi;

    @GetMapping("/{token}")
    @ResponseStatus(OK)
    public List<FriendDto> getFriends(@PathVariable String token) {
        return friendFinderApi.findFriendOf(UuidValidator.validate(token))
                .stream()
                .map(FriendDtoMapper::UserToFriendDto)
                .toList();
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public void createFriend(@RequestBody FriendCreationRequest request) {
        friendCreatorApi.create(UuidValidator.validate(request.userToken()), request.friendPseudo());
    }

}
