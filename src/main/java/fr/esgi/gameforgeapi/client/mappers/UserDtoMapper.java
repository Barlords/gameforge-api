package fr.esgi.gameforgeapi.client.mappers;

import fr.esgi.gameforgeapi.client.dto.user.UserCreationRequest;
import fr.esgi.gameforgeapi.client.dto.user.UserDto;
import fr.esgi.gameforgeapi.client.dto.user.UserFriendOrNotDto;
import fr.esgi.gameforgeapi.domain.functional.models.User;

import java.util.UUID;

public interface UserDtoMapper {

    static UserDto toDto(User domain) {
        return new UserDto(
                domain.getId(),
                domain.getEmail(),
                domain.getPseudo(),
                domain.getToken(),
                domain.getTokenDate(),
                domain.isNewsletterSubscribed(),
                domain.getPlayedGames(),
                domain.getWonGames()
        );
    }

    static UserFriendOrNotDto toFriendOrNotDto(User domain, boolean isFriend) {
        return new UserFriendOrNotDto(
                domain.getId(),
                domain.getEmail(),
                domain.getPseudo(),
                isFriend
        );
    }

    static User creationRequestToDomain(UserCreationRequest request) {
        return User.builder()
                .id(UUID.randomUUID())
                .email(request.email())
                .password(request.password())
                .pseudo(request.pseudo())
                .newsletterSubscribed(request.newsletterSubscription())
                .build();
    }
}
