package fr.esgi.gameforgeapi.client.dto.user;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record UserCreationRequest(
        @NotNull @JsonProperty("email")      String email,
        @NotNull @JsonProperty("password")   String password,
        @NotNull @JsonProperty("pseudo")     String pseudo,
        @NotNull @JsonProperty("newsletterSubscription") boolean newsletterSubscription
) {
}
