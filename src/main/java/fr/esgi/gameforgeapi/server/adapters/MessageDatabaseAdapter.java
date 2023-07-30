package fr.esgi.gameforgeapi.server.adapters;

import fr.esgi.gameforgeapi.domain.functional.models.Message;
import fr.esgi.gameforgeapi.domain.ports.server.MessagePersistenceSpi;
import fr.esgi.gameforgeapi.server.mappers.MessageEntityMapper;
import fr.esgi.gameforgeapi.server.repositories.MessageRepository;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageDatabaseAdapter implements MessagePersistenceSpi {

    private final MessageRepository repository;

    @Override
    @Transactional
    public Message save(Message o) {
        return MessageEntityMapper.toDomain(repository.save(MessageEntityMapper.fromDomain(o)));
    }

    @Override
    @Transactional
    public List<Message> findAll() {
        return repository.findAll().stream().map(MessageEntityMapper::toDomain).toList();
    }

    @Override
    @Transactional
    public Optional<Message> findById(UUID id) {
        return repository.findMessageEntityById(id).map(MessageEntityMapper::toDomain);
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public List<Message> findDiscussionWith(UUID userId, UUID friendId) {
        return repository.findMessageEntitiesBySenderIdOrReceiverId(userId, userId).stream()
                .map(MessageEntityMapper::toDomain)
                .filter(message -> (message.getSenderId().equals(userId) && message.getReceiverId().equals(friendId))
                        || (message.getSenderId().equals(friendId) && message.getReceiverId().equals(userId)))
                .toList();
    }

    @Override
    @Transactional
    public List<Message> findBySenderId(UUID senderId) {
        return repository.findMessageEntitiesBySenderIdOrderByReceiverId(senderId).stream().map(MessageEntityMapper::toDomain).toList();
    }

    @Override
    @Transactional
    public List<Message> findByReceiverId(UUID receiverId) {
        return repository.findMessageEntitiesByReceiverIdOrderBySenderId(receiverId).stream().map(MessageEntityMapper::toDomain).toList();
    }
}
