package fr.esgi.gameforgeapi.client.mappers;

import fr.esgi.gameforgeapi.client.dto.friend.FriendDto;
import fr.esgi.gameforgeapi.domain.functional.models.User;

public interface FriendDtoMapper {

    static FriendDto UserToFriendDto(User domain) {
        return new FriendDto(
                domain.getId(),
                domain.getEmail(),
                domain.getPseudo()
        );
    }

}
