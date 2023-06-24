package fr.esgi.gameforgeapi.server.repositories;

import fr.esgi.gameforgeapi.server.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import io.vavr.control.Option;

import java.util.List;
import java.util.UUID;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface UserRepository extends GenericRepository<UserEntity, UUID> {

    Option<UserEntity> findUserEntityByEmailAndPassword(String email, String password);

    Option<UserEntity> findUserEntityByPseudoAndPassword(String pseudo, String password);

}
