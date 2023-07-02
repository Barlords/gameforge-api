package fr.esgi.gameforgeapi.client.mappers;

import fr.esgi.gameforgeapi.client.dto.UserCreationRequest;
import fr.esgi.gameforgeapi.client.dto.UserDto;
import fr.esgi.gameforgeapi.domain.functional.models.User;
import fr.esgi.gameforgeapi.server.entities.UserEntity;

public interface UserDtoMapper {

    static UserDto toDto(User domain) {
        return new UserDto(
                domain.getEmail(),
                domain.getPseudo(),
                domain.getToken(),
                domain.getTokenDate()
        );
    }

    static User userCreationRequest(UserCreationRequest request) {
        return User.builder()
                .email(request.email())
                .password(request.password())
                .pseudo(request.pseudo())
                .build();
    }


}
