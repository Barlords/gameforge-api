package fr.esgi.gameforgeapi.client.dto.channel;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record ChannelCreationRequest(
        @NotNull @JsonProperty("members")    List<String> membersPseudo
) {
}
