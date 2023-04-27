package fr.esgi.gameforgeapi.domain.ports.server;

import io.vavr.control.Option;

import java.util.List;
import java.util.UUID;

public interface PersistenceSpi<T, ID> {

    T save(T o);

    List<T> findAll();

    Option<T> findById(ID id);

}
