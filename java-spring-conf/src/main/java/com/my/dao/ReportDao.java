package com.my.dao;

import com.my.domain.Report;
import org.hibernate.Session;

import java.util.List;


public class ReportDao extends AbstractDao<Report> {

    @Override
    public String getFindAll() {
        return "from Report";
    }

    @Override
    protected String getDeletById() {
        return "delete from Report where id = :id";
    }

    @Override
    public Report find(Session session, Long id) {
        return session.get(Report.class, id);
    }

    @Override
    protected boolean delete(Session session, Report report) {
        try  {
            session.delete(report);
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    @Override
    protected boolean save(Session session, Report report) {
        try  {
            session.save(report);
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    @Override
    protected boolean update(Session session, Report report) {
        try  {
            session.update(report);
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    @Override
    protected List<Report> findAll(Session session) {
        return session.createQuery("SELECT re FROM Report re", Report.class).getResultList();
    }


}
