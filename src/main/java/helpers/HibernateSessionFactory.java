package helpers;

import db.models.Auto;
import db.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import static helpers.TestConfig.getProperty;

public class HibernateSessionFactory {
    private static final Logger logger = LoggerFactory.getLogger(HibernateSessionFactory.class);
    private static SessionFactory sessionFactory;

    private HibernateSessionFactory() {
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                sessionFactory = new Configuration()
                        .setProperty("hibernate.connection.url", getProperty("db.Url"))
                        .addAnnotatedClass(User.class)
                        .addAnnotatedClass(Auto.class)
                        .buildSessionFactory();
            } catch (Exception e) {
                logger.debug("Исключение!{}", String.valueOf(e));
            }
        }
        return sessionFactory;
    }

}
