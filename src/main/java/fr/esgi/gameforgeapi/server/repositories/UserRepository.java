package fr.esgi.gameforgeapi.server.repositories;

import fr.esgi.gameforgeapi.domain.functional.models.User;
import fr.esgi.gameforgeapi.server.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    Optional<User> findByToken(UUID token);

    Optional<User> findByEmail(String email);

    Optional<User> findByPseudo(String pseudo);

    Optional<UserEntity> findByEmailAndPassword(String email, String password);

    Optional<UserEntity> findByPseudoAndPassword(String pseudo, String password);

    void deleteByToken(UUID token);

}
