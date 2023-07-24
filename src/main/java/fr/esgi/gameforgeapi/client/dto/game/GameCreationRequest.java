package fr.esgi.gameforgeapi.client.dto.game;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record GameCreationRequest(
        @NotNull @JsonProperty("user_token") String userToken,
        @NotNull @JsonProperty("name") String name,
        @NotNull @JsonProperty("description") String description
        ) {
}
