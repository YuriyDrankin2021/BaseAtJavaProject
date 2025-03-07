package db.dao;

import db.models.Auto;
import db.models.User;
import org.hibernate.Session;

import static helpers.HibernateSessionFactory.getSessionFactory;

public class UserDao extends AbstractHibernateDao<User> {

    public UserDao() {
        super(User.class);
    }

    public Auto findAutoById(int id) {
        Session session = getSessionFactory().openSession();
        Auto auto = session.getReference(Auto.class, id);
        session.close();
        return auto;
    }

}
