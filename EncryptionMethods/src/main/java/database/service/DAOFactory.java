package database.service;

import database.dao.Dao;
import database.dao.UserDao;
import database.entity.User;
import org.hibernate.SessionFactory;

public class DaoFactory {
    private static DaoFactory instance = null;
    private static SessionFactory sessionFactory = null;

    public static synchronized DaoFactory getInstance(SessionFactory sessionFactory) {
        if (instance == null) {
            instance = new DaoFactory(sessionFactory);
        }
        return instance;
    }

    private DaoFactory(SessionFactory sessionFactory) {
        DaoFactory.sessionFactory = sessionFactory;
    }

    public <T> Dao<T> getDaoBuClass(Class clazz) {
        return new Dao<T>(sessionFactory, clazz);
    }

    public UserDao getUserDao() {
        return new UserDao(sessionFactory, User.class);
    }

}
