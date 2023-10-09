package fr.esgi.gameforgeapi.client.resources;

import fr.esgi.gameforgeapi.client.dto.rank.RankDto;
import fr.esgi.gameforgeapi.client.dto.user.UserCreationRequest;
import fr.esgi.gameforgeapi.client.dto.user.UserDto;
import fr.esgi.gameforgeapi.client.dto.user.UserLogRequest;
import fr.esgi.gameforgeapi.client.mappers.RankDtoMapper;
import fr.esgi.gameforgeapi.client.mappers.UserDtoMapper;
import fr.esgi.gameforgeapi.client.services.EmailSenderService;
import fr.esgi.gameforgeapi.client.validator.UuidValidator;
import fr.esgi.gameforgeapi.domain.functional.exceptions.ResourceNotFoundException;
import fr.esgi.gameforgeapi.domain.functional.models.User;
import fr.esgi.gameforgeapi.domain.functional.services.user.UserModifierService;
import fr.esgi.gameforgeapi.domain.ports.client.rank.RankFinderApi;
import fr.esgi.gameforgeapi.domain.ports.client.user.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/ranks")
public class RankResource {

    private final RankFinderApi rankFinderApi;

    @GetMapping("")
    @ResponseStatus(OK)
    public List<RankDto> getGeneralRanks() {
        return rankFinderApi.findGeneralRanks()
                .stream()
                .map(RankDtoMapper::toDto)
                .toList();
    }

    @GetMapping("/game/{gameId}")
    @ResponseStatus(OK)
    public List<RankDto> getRanksByGame(@PathVariable String gameId) {
        return rankFinderApi.findByGame(UuidValidator.validate(gameId))
                .stream()
                .map(RankDtoMapper::toDto)
                .toList();
    }

}
