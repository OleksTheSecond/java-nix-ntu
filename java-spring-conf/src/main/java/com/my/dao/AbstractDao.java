package com.my.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;

public abstract class AbstractDao<E> {

    protected abstract String getFindAll();

    protected abstract String getDeletById();

    protected abstract E find(Session session, Long id);

    protected abstract boolean delete(Session session, E e);

    protected abstract boolean save(Session session, E e);

    protected abstract boolean update(Session session, E e);

    protected abstract List<E> findAll(Session session);



    public Session openSession() throws Exception {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession();
    }

    public E findById(Long id) throws Exception {
        Session session = openSession();
        return find(session, id);
    }

    public boolean delete(E e) throws Exception {
        Session session = openSession();
        Transaction tx1 = session.beginTransaction();
        boolean res = delete(session, e);
        if (res)
            tx1.commit();
        else
            tx1.rollback();

        return res;
    }

    public boolean deleteById(Long id) throws Exception {
        Session session = openSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery(getDeletById());
        query.setParameter("id", id);
        int res = query.executeUpdate();
        tx.commit();
        return res > 0;
    }


    public List<E> findAll() throws Exception {
        Session session = openSession();
        return findAll(session);
    }

    public boolean save(E e) throws Exception {
        Session session = openSession();
        Transaction tx1 = session.beginTransaction();
        boolean res = save(session, e);
        if (res)
            tx1.commit();
        else
            tx1.rollback();

        return res;
    }

    public boolean update(E e) throws Exception {
        Session session = openSession();
        Transaction tx1 = session.beginTransaction();
        boolean res = update(session, e);
        if (res)
            tx1.commit();
        else
            tx1.rollback();

        return res;
    }


}
