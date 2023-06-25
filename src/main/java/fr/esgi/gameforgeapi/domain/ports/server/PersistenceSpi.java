package fr.esgi.gameforgeapi.domain.ports.server;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersistenceSpi<T, ID> {

    T save(T o);

    List<T> findAll();

    Optional<T> findById(ID id);

    void deleteById(UUID id);

}
