package fr.esgi.gameforgeapi.server.repositories.dao.impl;

import com.tngtech.archunit.thirdparty.com.google.common.base.Preconditions;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public abstract class AbstractDao<T extends Serializable> {
    private Class<T> clazz;

    @Autowired
    private  EntityManagerFactory entityManagerFactory;

    public void setClazz(final Class<T> clazzToSet) {
        clazz = Preconditions.checkNotNull(clazzToSet);
    }

    public T findOne(final UUID id) {
        return (T) getCurrentSession().get(clazz, id);
    }

    public List<T> findAll() {
        return getCurrentSession().createQuery("from " + clazz.getName()).list();
    }

    public T save(final T entity) {
        Preconditions.checkNotNull(entity);
        getCurrentSession().saveOrUpdate(entity);
        return entity;
    }

    public T update(final T entity) {
        Preconditions.checkNotNull(entity);
        return (T) getCurrentSession().merge(entity);
    }

    public void delete(final T entity) {
        Preconditions.checkNotNull(entity);
        getCurrentSession().delete(entity);
    }

    public void deleteById(final UUID entityId) {
        final T entity = findOne(entityId);
        Preconditions.checkState(entity != null);
        delete(entity);
    }

    public Session getCurrentSession() {
        if (entityManagerFactory.unwrap(SessionFactory.class) == null) {
            throw new NullPointerException("factory is not a hibernate factory");
        }
        return entityManagerFactory.unwrap(SessionFactory.class).getCurrentSession();
    }

}