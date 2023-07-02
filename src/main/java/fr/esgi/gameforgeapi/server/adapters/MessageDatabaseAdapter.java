package fr.esgi.gameforgeapi.server.adapters;

import fr.esgi.gameforgeapi.domain.functional.models.Message;
import fr.esgi.gameforgeapi.domain.ports.server.MessagePersistenceSpi;
import fr.esgi.gameforgeapi.server.entities.MessageEntity;
import fr.esgi.gameforgeapi.server.mappers.MessageEntityMapper;
import fr.esgi.gameforgeapi.server.repositories.dao.IGenericDao;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageDatabaseAdapter implements MessagePersistenceSpi {

    private IGenericDao<MessageEntity> dao;

    @Override
    @Transactional
    public Message save(Message o) {
        return MessageEntityMapper.toDomain(dao.save(MessageEntityMapper.fromDomain(o)));
    }

    @Override
    @Transactional
    public List<Message> findAll() {
        return dao.findAll().stream().map(MessageEntityMapper::toDomain).toList();
    }

    @Override
    @Transactional
    public Option<Message> findById(UUID id) {
        return Option.of(MessageEntityMapper.toDomain(dao.findOne(id)));
    }


}
