package fr.esgi.gameforgeapi.client.mappers;

import fr.esgi.gameforgeapi.client.dto.message.MessageCreationRequest;
import fr.esgi.gameforgeapi.client.dto.message.MessageDto;
import fr.esgi.gameforgeapi.client.dto.rating.RatingCreationRequest;
import fr.esgi.gameforgeapi.client.dto.rating.RatingDto;
import fr.esgi.gameforgeapi.client.validator.UuidValidator;
import fr.esgi.gameforgeapi.domain.functional.models.Message;
import fr.esgi.gameforgeapi.domain.functional.models.Rating;

import java.util.UUID;

public interface RatingDtoMapper {

    static RatingDto toDto(Rating domain) {
        return new RatingDto(
                domain.getId(),
                domain.getUserId(),
                domain.getGameId(),
                domain.getRate()
        );
    }

    static Rating creationRequestToDomain(RatingCreationRequest request) {
        return Rating.builder()
                .id(UUID.randomUUID())
                .gameId(UuidValidator.validate(request.gameId()))
                .rate(request.rate())
                .build();
    }

}
