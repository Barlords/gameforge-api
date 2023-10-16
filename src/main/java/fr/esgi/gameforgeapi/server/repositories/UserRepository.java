package fr.esgi.gameforgeapi.server.repositories;

import fr.esgi.gameforgeapi.server.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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
            value = "SELECT u.*, u.won_games/u.played_games as winrate FROM user u WHERE played_games > 0 ORDER BY winrate",
            nativeQuery = true
    )
    List<UserEntity> findActiveUser();

    void deleteByToken(UUID token);

}
