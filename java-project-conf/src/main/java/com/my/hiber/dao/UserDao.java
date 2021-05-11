package com.my.hiber.dao;

import com.my.domain.User;
import org.hibernate.Session;

import java.util.List;

public class UserDao extends AbstractDao<User> {

    @Override
    public String getFindAll() {
        return "from User";
    }

    @Override
    protected String getDeletById() {
        return "delete from User where userId = :id";
    }
    @Override
    public User find(Session session, Long id) {
        return session.get(User.class, id);
    }

    @Override
    protected boolean delete(Session session, User user) {
        try  {
            session.delete(user);
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    @Override
    protected boolean save(Session session, User user) {

        try  {
            session.save(user);
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    @Override
    protected boolean update(Session session, User user) {
        try  {
            session.update(user);
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    @Override
    protected List<User> findAll(Session session) {
        return session.createQuery("SELECT us from User us", User.class).getResultList();
    }

    public List<User> findAllSpeakers() throws Exception {
        Session session = openSession();
        return session.createQuery("SELECT us FROM User us WHERE roleId = 2", User.class).getResultList();
    }
}
