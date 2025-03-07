package db.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

import static helpers.HibernateSessionFactory.getSessionFactory;

public class AbstractHibernateDao<T> {
    private final Class<T> clazz;

    public AbstractHibernateDao(final Class<T> clazzToSet)   {
        this.clazz = clazzToSet;
    }

    public T getById(final long id) {
        Session session = getCurrentSession();
        T result = session.get(clazz, id);
        session.close();
        return result;
    }

    public List<T> getItems(int offset, int count) {
        Session session = getCurrentSession();
        List<T> result = session.createSelectionQuery("From"+ clazz.getName(), clazz)
                .setFirstResult(offset)
                .setMaxResults(count)
                .getResultList();
        session.close();
        return result;
    }

    public List<T> findAll() {
        Session session = getCurrentSession();
        List<T> result = session.createSelectionQuery("From"+ clazz.getName(), clazz).getResultList();
        session.close();
        return result;
    }

    public T create(final T entity) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.persist(entity);
        transaction.commit();
        session.close();
        return entity;
    }

    public T update(final T entity) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();
        T result = session.merge(entity);
        transaction.commit();
        session.close();
        return result;
    }

    public void delete(final T entity) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.remove(entity);
        transaction.commit();
        session.close();
    }

    public void deleteById(final long entityId) {
        final T entity = getById(entityId);
        delete(entity);
    }

    protected Session getCurrentSession() {
        return getSessionFactory().openSession();
    }
}
