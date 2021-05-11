package com.my.hiber.dao;

import com.my.domain.Conference;
import com.my.domain.Report;
import com.my.domain.User;

import java.util.List;

public class TestDao {

    public static void main(String[] args) throws Exception {
        AbstractDao dao = new ConfDao();
        List<Conference> userList = dao.findAll();

        dao = new UserDao();
        List<User> users =  dao.findAll();


        dao = new ReportDao();
        List<Report> reports = dao.findAll();


        dao = new ConfDao();
        List<Conference> conferenceList = dao.findAll();
        for (Conference conference : conferenceList) {
            int i = 1;
            for (Report report : reports) {
                if (report.getConferenceId().equals(conference.getId())) {
                    conference.setReportsCount(i);
                    i++;
                }
            }
            dao.update(conference);
        }
        for (Conference conference : conferenceList) {
            System.out.println(conference);
        }
    }
}
