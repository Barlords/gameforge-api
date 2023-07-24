package fr.esgi.gameforgeapi.domain.functional.models;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.time.LocalDate;
import java.util.UUID;

@Value
@Builder
public class Action {

    @Builder.Default
    UUID id = UUID.randomUUID();

    @With
    UUID lobbyId;

    @With
    UUID userId;

    @With
    LocalDate actionTime;

    @With
    String action;

}
