package fr.esgi.gameforgeapi.client.mappers;

import fr.esgi.gameforgeapi.client.dto.user.UserCreationRequest;
import fr.esgi.gameforgeapi.client.dto.user.UserDto;
import fr.esgi.gameforgeapi.domain.functional.models.User;

import java.util.UUID;

public interface UserDtoMapper {

    static UserDto toDto(User domain) {
        return new UserDto(
                domain.getEmail(),
                domain.getPseudo(),
                domain.getToken(),
                domain.getTokenDate()
        );
    }

    static User creationRequestToDomain(UserCreationRequest request) {
        return User.builder()
                .id(UUID.randomUUID())
                .email(request.email())
                .password(request.password())
                .pseudo(request.pseudo())
                .build();
    }
}
