package fr.esgi.gameforgeapi.domain.functional.models;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.time.LocalDate;
import java.util.UUID;

@Value
@Builder
public class Friend {

    @Builder.Default
    UUID id = UUID.randomUUID();

    @With
    UUID userId;

    @With
    UUID friendId;

    @With
    LocalDate creationDate;

}
