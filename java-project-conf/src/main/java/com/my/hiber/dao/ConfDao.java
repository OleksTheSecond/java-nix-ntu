package com.my.hiber.dao;

import com.my.domain.Conference;
import org.hibernate.Session;

import java.util.List;

public class ConfDao extends AbstractDao<Conference> {

    @Override
    protected String getFindAll() {
        return "from Conference";
    }

    @Override
    protected String getDeletById() {
        return "delete from Conference where id = :id";
    }

    @Override
    protected Conference find(Session session, Long id) {
        return session.get(Conference.class, id);
    }

    @Override
    protected boolean delete(Session session, Conference conference) {
        try  {
            session.delete(conference);
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    @Override
    protected boolean save(Session session, Conference conference) {
        try  {
            session.save(conference);
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    @Override
    protected boolean update(Session session, Conference conference) {
        try  {
            session.update(conference);
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    @Override
    protected List<Conference> findAll(Session session) {
        return session.createQuery("SELECT conf FROM Conference conf", Conference.class).getResultList();

    }
}
