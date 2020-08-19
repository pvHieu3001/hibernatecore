package com.smartosc.training.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

/**
 * Spring-Boot-Hibernate
 *
 * @author Hieupv
 * @created_at 10/08/2020 - 2:19 PM
 * @created_by Hieupv
 * @since 10/08/2020
 */
public abstract  class AbstractCrudDao<T> {
    private final Session session;
    private final Class<T> entityClass;
    private final String entityName;

    protected AbstractCrudDao(Session session, Class<T> entityClass, String entityName) {
        this.session = session;
        this.entityClass = entityClass;
        this.entityName = entityName;
    }
    public T save(T entity) {
        session.save(entity);
        return entity;
    }
    public void delete(T entity) {
        Transaction transaction = session.beginTransaction();
        session.delete(entity);
        transaction.commit();
    }

    public T findById(long id) {
        return session.find(entityClass, id);
    }
    public List<T> findAll() {
        CriteriaQuery<T> query = session.getCriteriaBuilder().createQuery(entityClass);
        query.select(query.from(entityClass));
        return session.createQuery(query).getResultList();
    }
}
