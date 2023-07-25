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
    String srcPath;

    @With
    String description;

    @With
    String picturePresentation;

    @With
    UUID creatorId;

    @With
    LocalDate creationDate;

}
