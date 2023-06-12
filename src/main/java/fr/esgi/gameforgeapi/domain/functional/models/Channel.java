package fr.esgi.gameforgeapi.domain.functional.models;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.time.LocalDate;
import java.util.UUID;

@Value
@Builder
public class Channel {

    @Builder.Default
    UUID id = UUID.randomUUID();

    @With
    UUID friendId1;

    @With
    UUID friendId2;

    @With
    LocalDate creationDate;

    @With
    UUID token;

}
