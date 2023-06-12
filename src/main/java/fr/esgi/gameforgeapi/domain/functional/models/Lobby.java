package fr.esgi.gameforgeapi.domain.functional.models;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.time.LocalDate;
import java.util.UUID;

@Value
@Builder
public class Lobby {

    @Builder.Default
    UUID id = UUID.randomUUID();

    @With
    UUID gameId;

    @With
    LocalDate creationDate;

    @With
    LocalDate startDate;

    @With
    boolean finished;

    @With
    UUID winner;

}
