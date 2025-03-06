package db.dao;

import db.models.Auto;
import db.models.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

import static helpers.HibernateSessionFactory.getSessionFactory;

public class UserDao {
    public User findById(int id){
        Session session = getSessionFactory().openSession();
        User user = session.get(User.class, id);
        session.close();
        return user;
    }

    public void save(User user){
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(user);
        transaction.commit();
        session.close();
    }

    public void update(User user){
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(user);
        transaction.commit();
        session.close();
    }

    public void delete(User user){
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(user);
        transaction.commit();
        session.close();
    }

    public Auto findAutoById(int id){
        Session session = getSessionFactory().openSession();
        Auto auto = session.getReference(Auto.class, id);
        session.close();
        return auto;
    }

    public List<User> findAll(){
        Session session = getSessionFactory().openSession();
        List<User> users = session.createSelectionQuery("From User", User.class).getResultList();
        session.close();
        return users;
    }
}
