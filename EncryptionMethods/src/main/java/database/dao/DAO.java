/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.dao;

import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

/**
 * @author Алексей
 */
public class Dao<T> {

    protected SessionFactory sessionFactory;
    protected Class clazz;

    public Dao(SessionFactory sessionFactory, Class clazz) {
        this.sessionFactory = sessionFactory;
        this.clazz = clazz;
    }

    public T getEntityByStringProperty(String properties, String value) {
        T obj = null;
        try (Session session = this.sessionFactory.openSession()) {
            Criteria criteria = session.createCriteria(clazz);
            obj = (T) criteria.add(Restrictions.eq(properties, value)).uniqueResult();
        } catch (Exception ex) {
        }
        return obj;
    }

    public void addObject(T object) {
        Transaction transaction = null;
        try (Session session = this.sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(object);
            transaction.commit();
        } catch (Exception ex) {
            rollback(transaction);
        }
    }

    public void deleteObject(Long id) {
        Transaction transaction = null;
        try (Session session = this.sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            T object = (T) session.load(clazz, id);
            session.delete(object);
            transaction.commit();
        } catch (Exception var7) {
            rollback(transaction);
        }
    }

    private void rollback(Transaction transaction) {
        if (transaction != null) {
            transaction.rollback();
        }
    }
}
