package fr.esgi.gameforgeapi.domain.functional.services;


import fr.esgi.gameforgeapi.domain.functional.exceptions.TokenNotValidException;
import fr.esgi.gameforgeapi.domain.functional.models.User;
import fr.esgi.gameforgeapi.domain.ports.client.user.UserFinderApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class TokenControllerService {

    private final UserFinderApi userFinderApi;

    public User getUser(UUID token) {
        return userFinderApi.findByToken(token)
                .orElseThrow(() -> new TokenNotValidException(token.toString()));
    }

}
