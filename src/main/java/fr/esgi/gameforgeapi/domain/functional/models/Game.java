package fr.esgi.gameforgeapi.domain.functional.models;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.time.LocalDate;
import java.util.UUID;

@Value
@Builder
public class Game {

    @Builder.Default
    UUID id = UUID.randomUUID();

    @With
    String name;

    @With
    String SrcPath;

    @With
    LocalDate creationDate;

}
