package fr.esgi.gameforgeapi.server.repositories;

import fr.esgi.gameforgeapi.server.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findByVerificationCode(String code);

    List<UserEntity> findByNewsletterSubscribed(boolean newsletterSubscribed);

    Optional<UserEntity> findByToken(UUID token);

    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByPseudo(String pseudo);

    Optional<UserEntity> findByEmailAndPassword(String email, String password);

    Optional<UserEntity> findByPseudoAndPassword(String pseudo, String password);

    @Query(
            value = "SELECT u.*, u.won_games/u.played_games as winrate FROM user u WHERE played_games > 0 ORDER BY winrate DESC, u.played_games DESC",
            nativeQuery = true
    )
    List<UserEntity> findActiveUserOrderByWinrate();

    @Query(
            value = "SELECT u.* FROM user u WHERE u.pseudo LIKE CONCAT('%', :string_to_search, '%')",
            nativeQuery = true
    )
    List<UserEntity> findUsersByPseudo(@Param("string_to_search") String string_to_search);

    void deleteByToken(UUID token);

    @Query("UPDATE FriendEntity f SET f.accepted = true WHERE (f.userId = ?1 AND f.friendId = ?2) OR (f.userId = ?2 AND f.friendId = ?1)")
    @Modifying
    void acceptFriend(UUID userId, UUID friendId);

    @Query("SELECT u FROM UserEntity u JOIN SessionEntity s ON u.id = s.userId WHERE s.quitDate IS NULL AND s.lobbyId = ?1")
    List<UserEntity> findActiveUsersInLobby(UUID id);

}
