package fr.esgi.gameforgeapi.client.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record UserCreationRequest(

        @JsonProperty("email")      String email,
        @JsonProperty("password")   String password,
        @JsonProperty("pseudo")     String pseudo

) {
}
