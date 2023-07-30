package fr.esgi.gameforgeapi.server.adapters;

import fr.esgi.gameforgeapi.domain.functional.models.Friend;
import fr.esgi.gameforgeapi.domain.functional.models.User;
import fr.esgi.gameforgeapi.domain.ports.server.FriendPersistenceSpi;
import fr.esgi.gameforgeapi.domain.ports.server.UserPersistenceSpi;
import fr.esgi.gameforgeapi.server.mappers.FriendEntityMapper;
import fr.esgi.gameforgeapi.server.mappers.UserEntityMapper;
import fr.esgi.gameforgeapi.server.repositories.FriendRepository;
import fr.esgi.gameforgeapi.server.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FriendDatabaseAdapter implements FriendPersistenceSpi {

    private final FriendRepository repository;


    @Override
    @Transactional
    public List<Friend> findFriendsOf(UUID userId) {
        return repository.findFriendEntitiesByUserIdEqualsOrFriendIdEquals(userId, userId)
                .stream()
                .map(FriendEntityMapper::toDomain)
                .toList();
    }

    @Override
    @Transactional
    public Friend save(Friend o) {
        return FriendEntityMapper.toDomain(repository.save(FriendEntityMapper.fromDomain(o)));
    }

    @Override
    @Transactional
    public List<Friend> findAll() {
        return repository.findAll().stream().map(FriendEntityMapper::toDomain).toList();
    }

    @Override
    @Transactional
    public Optional<Friend> findById(UUID id) {
        return repository.findById(id).map(FriendEntityMapper::toDomain);
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}
