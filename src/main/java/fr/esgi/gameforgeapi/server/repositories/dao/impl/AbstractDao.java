package fr.esgi.gameforgeapi.server.repositories.dao.impl;

import com.tngtech.archunit.thirdparty.com.google.common.base.Preconditions;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Transactional
public abstract class AbstractDao<T extends Serializable> {


    @PersistenceContext
    private EntityManager entityManager;

    private Class<T> clazz;
    
    @Autowired
    private Session session;

    @Autowired
    private  EntityManagerFactory entityManagerFactory;
    
    public void setClazz(final Class<T> clazzToSet) {

        clazz = Preconditions.checkNotNull(clazzToSet);
        System.out.println(clazz.getName());
    }

    public T findOne(final UUID id) {
        return (T) session.get(clazz, id);
    }

    public List<T> findAll() {
        return session.createQuery("from " + clazz.getName()).list();
    }

    public T save(final T entity) {
        Preconditions.checkNotNull(entity);
        session.persist(entity);
        return entity;
    }

    public T update(final T entity) {
        Preconditions.checkNotNull(entity);
        return (T) session.merge(entity);
    }

    public void delete(final T entity) {
        Preconditions.checkNotNull(entity);
        session.delete(entity);
    }

    public void deleteById(final UUID entityId) {
        final T entity = findOne(entityId);
        Preconditions.checkState(entity != null);
        delete(entity);
    }
    

    public void flush() {
        entityManager.flush();
    }

}