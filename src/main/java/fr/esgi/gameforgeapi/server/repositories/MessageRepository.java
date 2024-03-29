package fr.esgi.gameforgeapi.server.repositories;

import fr.esgi.gameforgeapi.server.entities.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface MessageRepository extends JpaRepository<MessageEntity, UUID> {

    Optional<MessageEntity> findMessageEntityById(UUID id);

    List<MessageEntity> findMessageEntitiesByChannelIdOrderBySendDate(UUID channelId);

    List<MessageEntity> findMessageEntitiesBySenderIdOrderBySendDate(UUID senderId);

}
