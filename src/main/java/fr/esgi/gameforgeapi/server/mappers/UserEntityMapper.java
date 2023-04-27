package fr.esgi.gameforgeapi.server.mappers;

import fr.esgi.gameforgeapi.domain.functional.models.User;
import fr.esgi.gameforgeapi.server.entities.UserEntity;

public interface UserEntityMapper {

    static User toDomain(UserEntity entity) {
        return User.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .pseudo(entity.getPseudo())
                .build();
    }

    static UserEntity fromDomain(User domain) {
        return UserEntity.builder()
                .id(domain.getId())
                .email(domain.getEmail())
                .password(domain.getPassword())
                .pseudo(domain.getPseudo())
                .build();
    }

}
