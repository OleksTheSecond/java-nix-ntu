package com.my.services;

import com.my.dao.AbstractDao;
import com.my.dao.ReportDao;
import com.my.dao.UserDao;
import com.my.domain.Report;
import com.my.domain.User;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReportService {
    private ReportDao reportDao = new ReportDao();

    public List<Report> findAll() throws Exception {
        return reportDao.findAll();
    }

    public List<String> getTitles() throws Exception {
        List<String> titles = new ArrayList<>();
        for (Report report : findAll()) {
            titles.add(report.getTitle());
        }
        return titles;
    }

    public ModelAndView getListForPrint() throws Exception {
        AbstractDao dao = new ReportDao();
        List<Report> reports = ((ReportDao) dao).findAll();
        dao = new UserDao();
        List<User> users = ((UserDao) dao).findAll();

        List<String> speakersWithRep = new ArrayList<>();
        List<Long> repWithSpeakers = new ArrayList<>();

        for (Report report1 : reports) {
            for (User user : users) {

                if (report1.getUserId().equals(user.getId())) {
                    speakersWithRep.add(
                            user.getFirstName() + " " + user.getLastName());
                    repWithSpeakers.add(report1.getId());
                    break;
                }
            }
        }
        List<String> titles = getTitles();

        ModelAndView model = new ModelAndView("reports/repList");
        model.addObject("titles", titles);
        model.addObject("speakersWithRep", speakersWithRep);
        model.addObject("repWithSpeakers", repWithSpeakers);
        model.addObject("end", titles.size() - 1);
        return model;
    }

    public List<Report> findAllById(Long id) throws Exception {
        return reportDao.findAllById(id);
    }


    public String reportCheck(String title) {
        String error = "OK";
        if (title.trim().equals("")) {
            error = "Заполните тему";
        }
        return error;
    }

}
