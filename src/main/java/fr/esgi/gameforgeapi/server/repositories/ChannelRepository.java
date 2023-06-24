package fr.esgi.gameforgeapi.server.repositories;

import fr.esgi.gameforgeapi.server.entities.ActionEntity;
import fr.esgi.gameforgeapi.server.entities.ChannelEntity;
import fr.esgi.gameforgeapi.server.entities.GameEntity;
import io.vavr.control.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface ChannelRepository extends JpaRepository<ChannelEntity, UUID> {

}
