package fr.esgi.gameforgeapi.client.dto.channel;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.time.LocalDate;
import java.util.UUID;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record ChannelDto(
        UUID messageId,
        UUID senderId,
        UUID channelId,
        String content,
        LocalDate sendDate
) {

}