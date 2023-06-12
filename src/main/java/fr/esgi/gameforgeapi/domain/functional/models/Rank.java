package fr.esgi.gameforgeapi.domain.functional.models;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.time.LocalDate;
import java.util.UUID;

@Value
@Builder
public class Rank {

    @Builder.Default
    UUID id = UUID.randomUUID();

    @With
    UUID gameId;

    @With
    UUID userId;

    @With
    int playedGame;

    @With
    int winGame;

}
