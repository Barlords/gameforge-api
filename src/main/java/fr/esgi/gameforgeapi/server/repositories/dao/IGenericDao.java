package fr.esgi.gameforgeapi.server.repositories.dao;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public interface IGenericDao<T extends Serializable> {
    void setClazz(Class< T > clazzToSet);

    T findOne(final UUID id);

    List<T> findAll();

    T save(final T entity);

    T update(final T entity);

    void delete(final T entity);

    void deleteById(final UUID entityId);
}