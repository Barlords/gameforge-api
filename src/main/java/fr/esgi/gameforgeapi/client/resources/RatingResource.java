package fr.esgi.gameforgeapi.client.resources;

import fr.esgi.gameforgeapi.client.dto.rating.RatingCreationRequest;
import fr.esgi.gameforgeapi.client.dto.rating.RatingDto;
import fr.esgi.gameforgeapi.client.mappers.GameDtoMapper;
import fr.esgi.gameforgeapi.client.mappers.RatingDtoMapper;
import fr.esgi.gameforgeapi.client.validator.UuidValidator;
import fr.esgi.gameforgeapi.domain.functional.exceptions.ResourceNotFoundException;
import fr.esgi.gameforgeapi.domain.ports.client.rating.RatingCreatorApi;
import fr.esgi.gameforgeapi.domain.ports.client.rating.RatingFinderApi;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/ratings")
public class RatingResource {

    private final RatingCreatorApi ratingCreatorApi;

    private final RatingFinderApi ratingFinderApi;

    @GetMapping("/{game_id}")
    @ResponseStatus(OK)
    public List<RatingDto> getRatings(@PathVariable("game_id") String gameId) {
        return ratingFinderApi.findByGameId(UuidValidator.validate(gameId))
                .stream()
                .map(RatingDtoMapper::toDto)
                .toList();
    }

    @GetMapping("?user_token={user_token}&game_id={game_id}")
    @ResponseStatus(OK)
    public RatingDto getRatingByUserAndGame(@PathVariable("user_token") String user_token, @PathVariable("game_id") String gameId) {
        return ratingFinderApi.findByUserAndGameId(UuidValidator.validate(user_token), UuidValidator.validate(gameId))
                .map(RatingDtoMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("L'utilisateur n'a pas évalué ce jeux"));
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public void createRating(@RequestBody RatingCreationRequest request) {
        ratingCreatorApi.create(UuidValidator.validate(request.userToken()), RatingDtoMapper.creationRequestToDomain(request));
    }



}
