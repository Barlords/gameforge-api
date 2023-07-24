package fr.esgi.gameforgeapi.client.dto.game;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.time.LocalDate;
import java.util.UUID;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record GameDto(

        UUID id,

        String name,

        String description,

        String picturePresentation,

        UUID creatorId,

        LocalDate creationDate

) {

}