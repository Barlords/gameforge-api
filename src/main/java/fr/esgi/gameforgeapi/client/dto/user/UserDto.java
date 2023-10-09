package fr.esgi.gameforgeapi.client.dto.user;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.time.LocalDate;
import java.util.UUID;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record UserDto(
        String email,
        String pseudo,
        UUID token,
        LocalDate tokenDate,
        boolean newsletterSubscribed,
        int playedGames,
        int wonGames
) {

}
