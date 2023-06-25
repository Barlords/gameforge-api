package fr.esgi.gameforgeapi.domain.functional.models;

import fr.esgi.gameforgeapi.server.entities.UserEntity;
import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.time.LocalDate;
import java.util.UUID;

@Value
@Builder
public class User {

    @With
    UUID id;

    @With
    String email;

    @With
    String password;

    @With
    String pseudo;

    @With
    UUID token;

    @With
    LocalDate tokenDate;

}
