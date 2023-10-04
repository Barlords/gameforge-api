package fr.esgi.gameforgeapi.server.repositories.dao.impl;

import com.tngtech.archunit.thirdparty.com.google.common.base.Preconditions;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public abstract class AbstractDao<T extends Serializable> {

    private Class<T> clazz;

    @Autowired
    private SessionFactory sessionFactory;


    public void setClazz(final Class<T> clazzToSet) {
        clazz = Preconditions.checkNotNull(clazzToSet);
    }

    @Transactional

    public T findOne(final UUID id) {
        return (T) getCurrentSession().get(clazz, id);
    }

    @Transactional
    public List findAll() {
        return getCurrentSession().createQuery("from " + clazz.getName()).list();
    }

    @Transactional
    public T save(final T entity) {
        Preconditions.checkNotNull(entity);
        try {
            getCurrentSession().beginTransaction();
            getCurrentSession().merge(entity);
            getCurrentSession().getTransaction().commit();
            return entity;
        } catch (Exception e) {
            getCurrentSession().getTransaction().rollback();
            return null;
        }
    }

    @Transactional

    public T update(final T entity) {
        Preconditions.checkNotNull(entity);
        return (T) getCurrentSession().merge(entity);
    }

    @Transactional

    public void delete(final T entity) {
        Preconditions.checkNotNull(entity);
        getCurrentSession().delete(entity);
    }

    @Transactional
    public void deleteById(final UUID entityId) {
        final T entity = findOne(entityId);
        Preconditions.checkState(entity != null);
        delete(entity);
    }

    @Transactional
    public void flush() {
        getCurrentSession().flush();
    }

    @Transactional
    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }


}