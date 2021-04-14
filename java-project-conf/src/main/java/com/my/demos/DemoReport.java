package com.my.demos;

import com.my.dao.ReportDao;
import com.my.domain.Report;

import java.sql.SQLException;
import java.util.List;

public class DemoReport {

    public static void printList(List<?> list) {
        for (Object obj : list) {
            System.out.println(obj);
        }
    }

    public static void main(String[] args) throws SQLException {
        ReportDao reportDao = new ReportDao();

        Report report1 = new Report(1L, "Title#1", 2L);
        Report report2 = new Report(2L, "Title#2", 1L);
        Report report3 = new Report(3L, "Title#3", 1L);

        report1.setConferenceId(1L);
        report2.setConferenceId(2L);
        report3.setConferenceId(3L);

        //Add to base
        reportDao.create(report1);
        reportDao.create(report2);
        reportDao.create(report3);

        //Print all
        printList(reportDao.findAll());
        System.out.println("#################################");

        //Updaet second
        Report toUpdate = new Report(2L, "Title#UPDATED", 2L);
        toUpdate.setConferenceId(3L);
        reportDao.update(toUpdate);
        printList(reportDao.findAll());
        System.out.println("#################################");

        //Find by ID
        Report finded = (Report)(reportDao.findById(2L));
        System.out.println(finded);

        //Delete all
//        reportDao.deleteById(1L);
//        reportDao.deleteById(2L);
//        reportDao.deleteById(3L);


    }
}
