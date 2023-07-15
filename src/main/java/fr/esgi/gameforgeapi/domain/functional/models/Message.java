package fr.esgi.gameforgeapi.domain.functional.models;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.time.LocalDate;
import java.util.UUID;

@Value
@Builder
public class Message {

    @Builder.Default
    UUID id = UUID.randomUUID();

    @With
    UUID senderId;

    @With
    UUID channelId;

    @With
    String content;

    @With
    LocalDate sendDate;

}
