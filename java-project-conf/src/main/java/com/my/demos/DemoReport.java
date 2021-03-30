package com.my.demos;

import com.my.dao.ReportDao;
import com.my.domain.Report;

import java.util.List;

public class DemoReport {

    public static void printList(List<?> list) {
        for (Object obj : list) {
            System.out.println(obj);
        }
    }

    public static void main(String[] args) {
        ReportDao reportDao = new ReportDao();

        Report report1 = new Report(1, "Title#1", 2);
        Report report2 = new Report(2, "Title#2", 1);
        Report report3 = new Report(3, "Title#3", 1);

        report1.setConferenceId(1);
        report2.setConferenceId(2);
        report3.setConferenceId(3);

        //Add to base
        reportDao.create(report1);
        reportDao.create(report2);
        reportDao.create(report3);

        //Print all
        printList(reportDao.findAll());
        System.out.println("#################################");

        //Updaet second
        Report toUpdate = new Report(2, "Title#UPDATED", 2);
        toUpdate.setConferenceId(3);
        reportDao.update(toUpdate);
        printList(reportDao.findAll());
        System.out.println("#################################");

        //Find by ID
        Report finded = (Report)(reportDao.findById(2));
        System.out.println(finded);

        //Delete all
        reportDao.delete(report1);
        reportDao.delete(report2);
        reportDao.delete(report3);



    }
}
