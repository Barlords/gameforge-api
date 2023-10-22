package fr.esgi.gameforgeapi.domain.functional.services.user;

import fr.esgi.gameforgeapi.client.dto.user.UserFriendOrNotDto;
import fr.esgi.gameforgeapi.client.mappers.UserDtoMapper;
import fr.esgi.gameforgeapi.domain.functional.exceptions.ResourceNotFoundException;
import fr.esgi.gameforgeapi.domain.functional.models.User;
import fr.esgi.gameforgeapi.domain.ports.client.user.UserFinderApi;
import fr.esgi.gameforgeapi.domain.ports.server.FriendPersistenceSpi;
import fr.esgi.gameforgeapi.domain.ports.server.UserPersistenceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class UserFinderService implements UserFinderApi {

    private final UserPersistenceSpi spi;
    private final FriendPersistenceSpi friendPersistenceSpi;

    @Override
    public List<User> findAll() {
        return spi.findAll();
    }

    @Override
    public List<User> findByNewsletterSubscribed(boolean subscribed) {
        return spi.findByNewsletterSubscribed(subscribed);
    }

    @Override
    public Optional<User> findById(UUID id) {
        return spi.findById(id);
    }

    @Override
    public Optional<User> findByToken(UUID token) {
        return spi.findByToken(token);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return spi.findByEmail(email);
    }

    @Override
    public Optional<User> findByPseudo(String pseudo) {
        return spi.findByPseudo(pseudo);
    }

    @Override
    public List<User> findActiveUsers() {
        return spi.findActiveUsers();
    }

    @Override
    public List<User> findUsersByString(String string_to_search) {
        return spi.findUsersByString(string_to_search);
    }

    @Override
    public List<UserFriendOrNotDto> findUsersFriendOrNotByPseudo(String string_to_search, UUID userToken) {
        List<User> usersByPseudo = spi.findUsersByString(string_to_search);
        User user = spi.findByToken(userToken)
                .orElseThrow(() -> new ResourceNotFoundException("Aucun utilisateur ne correspond au token fournit"));;
        List<UserFriendOrNotDto> result = new ArrayList<>();
        for (User userInList : usersByPseudo) {
            if (userInList.getId().equals(user.getId())) {
                continue;
            }
            boolean isFriend = friendPersistenceSpi.isFriendOfOrAsked(user.getId(), userInList.getId());
            UserFriendOrNotDto dto = UserDtoMapper.toFriendOrNotDto(userInList, isFriend);
            result.add(dto);
        }

        return result;

    }
}
