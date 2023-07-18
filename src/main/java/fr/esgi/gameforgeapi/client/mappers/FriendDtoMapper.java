package fr.esgi.gameforgeapi.client.mappers;

import fr.esgi.gameforgeapi.client.dto.friend.FriendCreationRequest;
import fr.esgi.gameforgeapi.client.dto.friend.FriendDto;
import fr.esgi.gameforgeapi.client.dto.user.UserCreationRequest;
import fr.esgi.gameforgeapi.client.dto.user.UserDto;
import fr.esgi.gameforgeapi.domain.functional.models.Friend;
import fr.esgi.gameforgeapi.domain.functional.models.User;

import java.util.UUID;

public interface FriendDtoMapper {

    static FriendDto UserToFriendDto(User domain) {
        return new FriendDto(
                domain.getId(),
                domain.getEmail(),
                domain.getPseudo()
        );
    }

}
