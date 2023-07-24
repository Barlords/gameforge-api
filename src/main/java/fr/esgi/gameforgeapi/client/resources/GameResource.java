package fr.esgi.gameforgeapi.client.resources;


import fr.esgi.gameforgeapi.client.dto.game.GameCreationRequest;
import fr.esgi.gameforgeapi.client.dto.game.GameDto;
import fr.esgi.gameforgeapi.client.mappers.GameDtoMapper;
import fr.esgi.gameforgeapi.client.minio.MinioException;
import fr.esgi.gameforgeapi.client.minio.MinioService;
import fr.esgi.gameforgeapi.client.validator.UuidValidator;
import fr.esgi.gameforgeapi.domain.functional.exceptions.ResourceNotFoundException;
import fr.esgi.gameforgeapi.domain.ports.client.game.GameCreatorApi;
import fr.esgi.gameforgeapi.domain.ports.client.game.GameFinderApi;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/games")
public class GameResource {

    private final MinioService minioService;

    private final GameCreatorApi gameCreatorApi;

    private final GameFinderApi gameFinderApi;

    @GetMapping
    @ResponseStatus(OK)
    public List<GameDto> getGames() {
        return gameFinderApi.findAll()
                .stream()
                .map(GameDtoMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public GameDto getGameById(@PathVariable String id) {
        return gameFinderApi.findById(UuidValidator.validate(id))
                .map(GameDtoMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Le jeu : " + id + " est introuvable"));
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(CREATED)
    public GameDto createGame(
            @Valid @RequestPart("picture_presentation") MultipartFile pictureFile,
            @Valid @RequestPart("source_file") MultipartFile sourceFile,
            @Valid @RequestPart("config_file") MultipartFile configFile,
            @Valid @RequestPart("game_creation_request") GameCreationRequest request
    ) {
        String picturePath = request.name()+"/"+pictureFile.getOriginalFilename();
        String sourcePath = request.name()+"/"+sourceFile.getOriginalFilename();
        String configPath = request.name()+"/"+configFile.getOriginalFilename();
        try {
            minioService.upload(picturePath, pictureFile.getInputStream(), pictureFile.getContentType());
            minioService.upload(sourcePath, sourceFile.getInputStream(), sourceFile.getContentType());
            minioService.upload(configPath, configFile.getInputStream(), configFile.getContentType());
        } catch (MinioException e) {
            throw new IllegalStateException("The file cannot be upload on the internal storage. Please retry later", e);
        } catch (IOException e) {
            throw new IllegalStateException("The file cannot be read", e);
        }

        return GameDtoMapper.toDto(
                gameCreatorApi.create(
                        UuidValidator.validate(request.userToken()),
                        GameDtoMapper.creationRequestToDomain(request)
                                .withPicturePresentation(picturePath)
                                .withSourceFile(sourcePath)
                                .withConfigFile(configPath)
                )
        );
    }
}
