package db.dao;

import db.models.Auto;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

import static helpers.HibernateSessionFactory.getSessionFactory;

public class AutoDao {

    public Auto findById(int id){
        Session session = getSessionFactory().openSession();
        Auto auto = session.getReference(Auto.class, id);
        session.close();
        return auto;
    }

    public void save(Auto auto){
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(auto);
        transaction.commit();
        session.close();
    }

    public void update(Auto auto){
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(auto);
        transaction.commit();
        session.close();
    }

    public void delete(Auto auto){
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(auto);
        transaction.commit();
        session.close();
    }

    public Auto findAutoById(int id){
        Session session = getSessionFactory().openSession();
        Auto auto = session.getReference(Auto.class, id);
        session.close();
        return auto;
    }

    public List<Auto> findAll(){
        Session session = getSessionFactory().openSession();
        List<Auto> autoList = session.createSelectionQuery("From Auto", Auto.class).getResultList();
        session.close();
        return autoList;
    }
}
