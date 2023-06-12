package fr.esgi.gameforgeapi.server.repositories;

import fr.esgi.gameforgeapi.server.entities.MessageEntity;
import fr.esgi.gameforgeapi.server.entities.RankEntity;
import fr.esgi.gameforgeapi.server.entities.SessionEntity;
import io.vavr.control.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface RankRepository extends JpaRepository<RankEntity, UUID> {

    List<RankEntity> findAll();

    Option<RankEntity> findRankEntityById(UUID id);

}
