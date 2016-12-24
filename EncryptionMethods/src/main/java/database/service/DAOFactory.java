package database.service;

import database.dao.DAO;
import database.dao.UserDAO;
import database.entity.User;
import org.hibernate.SessionFactory;

public class DAOFactory {
    private static DAOFactory instance = null;
    private static SessionFactory sessionFactory = null;

    public static synchronized DAOFactory getInstance(SessionFactory sessionFactory) {
        if (instance == null) {
            instance = new DAOFactory(sessionFactory);
        }
        return instance;
    }

    private DAOFactory(SessionFactory sessionFactory) {
        DAOFactory.sessionFactory = sessionFactory;
    }

    public <T> DAO<T> getDaoBuClass(Class clazz) {
        return new DAO<T>(sessionFactory, clazz);
    }

    public UserDAO getUserDao() {
        return new UserDAO(sessionFactory, User.class);
    }

}
