package fr.esgi.gameforgeapi.domain.functional.models;

import fr.esgi.gameforgeapi.domain.functional.services.RandomStringGenerator;
import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.time.LocalDate;
import java.util.UUID;

@Value
@Builder
public class User {

    @Builder.Default
    @With
    UUID id = UUID.randomUUID();

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

    @Builder.Default
    @With
    boolean newsletterSubscribed = true;

    @Builder.Default
    @With
    boolean enabled = false;

    @Builder.Default
    @With
    String verificationCode = RandomStringGenerator.generateAlphanumericString(10);

}
