package database.dao;

import database.entity.User;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

/**
 * Created by Алексей on 24.12.2016.
 */
public class UserDAO extends DAO<User> {
    public UserDAO(SessionFactory sessionFactory, Class clazz) {
        super(sessionFactory, clazz);
    }

    public User getEntityByStringProperty(String properties, String value) {
        User user = null;
        try (Session session = this.sessionFactory.openSession()) {
            Criteria criteria = session.createCriteria(clazz);
            user = (User) criteria.add(Restrictions.eq(properties, value)).uniqueResult();
            Hibernate.initialize(user.getMethods());
        } catch (Exception ex) {
        }
        return user;
    }
}
