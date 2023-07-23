package fr.esgi.gameforgeapi.server.adapters;

import fr.esgi.gameforgeapi.domain.functional.models.Message;
import fr.esgi.gameforgeapi.domain.functional.models.Rating;
import fr.esgi.gameforgeapi.domain.ports.server.MessagePersistenceSpi;
import fr.esgi.gameforgeapi.domain.ports.server.RatingPersistenceSpi;
import fr.esgi.gameforgeapi.server.mappers.FriendEntityMapper;
import fr.esgi.gameforgeapi.server.mappers.MessageEntityMapper;
import fr.esgi.gameforgeapi.server.mappers.RatingEntityMapper;
import fr.esgi.gameforgeapi.server.repositories.MessageRepository;
import fr.esgi.gameforgeapi.server.repositories.RatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RatingDatabaseAdapter implements RatingPersistenceSpi {

    private final RatingRepository repository;

    @Override
    @Transactional
    public Rating save(Rating o) {
        return RatingEntityMapper.toDomain(repository.save(RatingEntityMapper.fromDomain(o)));
    }

    @Override
    @Transactional
    public List<Rating> findAll() {
        return repository.findAll().stream().map(RatingEntityMapper::toDomain).toList();
    }

    @Override
    @Transactional
    public Optional<Rating> findById(UUID uuid) {
        return repository.findById(uuid).map(RatingEntityMapper::toDomain);
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public List<Rating> findByGameId(UUID gameId) {
        return repository.findRatingEntitiesByGameId(gameId)
                .stream()
                .map(RatingEntityMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Rating> findByUserIdAndGameId(UUID userId, UUID gameId) {
        return repository.findRatingEntityByUserIdAndGameId(userId, gameId).map(RatingEntityMapper::toDomain);
    }
}
