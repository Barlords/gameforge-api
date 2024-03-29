package fr.esgi.gameforgeapi.client.resources;

import fr.esgi.gameforgeapi.client.dto.friend.FriendCreationRequest;
import fr.esgi.gameforgeapi.client.dto.user.UserDto;
import fr.esgi.gameforgeapi.client.mappers.UserDtoMapper;
import fr.esgi.gameforgeapi.client.validator.UuidValidator;
import fr.esgi.gameforgeapi.domain.ports.client.friend.FriendCreatorApi;
import fr.esgi.gameforgeapi.domain.ports.client.friend.FriendFinderApi;
import fr.esgi.gameforgeapi.domain.ports.client.friend.FriendUpdaterApi;
import fr.esgi.gameforgeapi.domain.ports.client.friend.FriendDeleterApi;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/friends")
public class FriendResource {

    private final FriendCreatorApi friendCreatorApi;

    private final FriendFinderApi friendFinderApi;

    private final FriendUpdaterApi friendUpdaterApi;

    private final FriendDeleterApi friendDeleterApi;

    @GetMapping("/{user_token}")
    @ResponseStatus(OK)
    public List<UserDto> getFriends(@PathVariable String user_token) {
        return friendFinderApi.findFriendOf(UuidValidator.validate(user_token))
                .stream()
                .map(UserDtoMapper::toDto)
                .toList();
    }

    @GetMapping("/asked_requests/{user_token}")
    @ResponseStatus(OK)
    public List<UserDto> getAskedRequests(@PathVariable String user_token) {
        return friendFinderApi.getAskedRequests(UuidValidator.validate(user_token))
                .stream()
                .map(UserDtoMapper::toDto)
                .toList();
    }

    @GetMapping("/sent_requests/{user_token}")
    @ResponseStatus(OK)
    public List<UserDto> getSentRequests(@PathVariable String user_token) {
        return friendFinderApi.getSentRequests(UuidValidator.validate(user_token))
                .stream()
                .map(UserDtoMapper::toDto)
                .toList();
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public void createFriend(@RequestBody FriendCreationRequest request) {
        friendCreatorApi.create(UuidValidator.validate(request.userToken()), UuidValidator.validate(request.friendId()));
    }

    @PatchMapping("/accept/{user_token}/{friend_id}")
    @ResponseStatus(CREATED)
    public void acceptFriend(@PathVariable String user_token, @PathVariable String friend_id) {
        System.out.println("token = ");
        System.out.println(user_token);
        System.out.println("id friend = ");
        System.out.println(friend_id);
        friendUpdaterApi.acceptFriend(UuidValidator.validate(user_token), UuidValidator.validate(friend_id));
    }

    @DeleteMapping("/delete/{user_token}/{friend_id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteFriend(@Valid  @PathVariable("user_token") String user_token, @Valid @PathVariable("friend_id") String friend_id) {
        friendDeleterApi.deleteFriend(
                UuidValidator.validate(user_token),
                UuidValidator.validate(friend_id)
        );
    }

}
