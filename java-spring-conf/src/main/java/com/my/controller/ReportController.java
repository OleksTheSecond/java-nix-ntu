package com.my.controller;

import com.my.dao.AbstractDao;
import com.my.dao.ConfDao;
import com.my.dao.ReportDao;
import com.my.dao.UserDao;
import com.my.domain.Conference;
import com.my.domain.Report;
import com.my.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ReportController {

    @RequestMapping(method = RequestMethod.GET, path = "addRep")
    public ModelAndView addRep() throws Exception {
        ModelAndView model = new ModelAndView("reports/AddReport");
        List<User> users = new UserDao().findAllSpeakers();
        model.addObject("user", users);
        List<Conference> conferenceList = new ConfDao().findAll();
        model.addObject("conf", conferenceList);
        return model;
    }

    @RequestMapping(method = RequestMethod.POST, path = "addRepPost")
    public String addRepPost(@RequestParam(value = "title") String title,
                               @RequestParam(value = "userId") String userId,
                               @RequestParam (value = "confId") String confId) {

        Long userID = Long.parseLong(userId);
        Long confID = Long.parseLong(confId);
        Report report = new Report(title, userID);
        report.setConferenceId(confID);

        ReportDao reportDao = new ReportDao();
        ConfDao confDao = new ConfDao();
        try {
            reportDao.save(report);
            Conference conference = confDao.findById(confID);
            conference.setReportsCount(conference.getReportsCount()+1);
            confDao.update(conference);

        } catch (Exception e) {
            return "error";
        }

        return "redirect:/indexRep";

    }

    @RequestMapping(method = RequestMethod.POST, path = "deleteRep")
    public String delete(@RequestParam(value = "id") String id) {
        Long ID = Long.parseLong(id);
        ReportDao reportDao = new ReportDao();
        try {
            reportDao.deleteById(ID);
        } catch (Exception e) {
            return "error";
        }
        return "redirect:/indexRep";
    }


    @RequestMapping(method = RequestMethod.GET, path = "indexRep")
    public ModelAndView startPage() {
        try {
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

            List<String> titles = new ArrayList<>();
            for (Report report : reports) {
                titles.add(report.getTitle());
            }

            ModelAndView model = new ModelAndView("reports/main");
            model.addObject("titles", titles);
            model.addObject("speakersWithRep", speakersWithRep);
            model.addObject("repWithSpeakers", repWithSpeakers);
            model.addObject("end", titles.size() - 1);
            return model;

        } catch (Exception ex) {
            return new ModelAndView("error");
        }
    }
}
