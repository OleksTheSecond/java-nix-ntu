package com.my.controller;

import com.my.dao.ConfDao;
import com.my.dao.ReportDao;
import com.my.dao.UserDao;
import com.my.domain.Conference;
import com.my.domain.Report;
import com.my.domain.User;
import com.my.services.ReportService;
import org.h2.engine.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ReportController {

    private ReportService service = new ReportService();

    @RequestMapping(method = RequestMethod.GET, path = "addRepSp")
    public ModelAndView addRepSp(HttpServletRequest request) throws Exception {
        ModelAndView model = new ModelAndView("reports/addReportSpeaker");
        HttpSession session = request.getSession();
        String ID = String.valueOf(session.getAttribute("MyId")) ;
        List<Conference> conferenceList = new ConfDao().findAll();
        model.addObject("conf", conferenceList);
        model.addObject("id", ID);
        return model;
    }


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
    public ModelAndView addRepPost(@RequestParam(value = "title") String title,
                           @RequestParam(value = "userId") String userId,
                           @RequestParam (value = "confId") String confId) throws Exception {

        if (!service.reportCheck(title).equals("OK")) {
            ModelAndView model = addRep();
            model.addObject("error", service.reportCheck(title));
            return model;
        }

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
            return new ModelAndView("error");
        }

        return new ModelAndView("redirect:/backToProfile");

    }

    @RequestMapping(method = RequestMethod.GET, path = "repList")
    public ModelAndView repList() {
        try {
            return service.getListForPrint();
        } catch (Exception ex) {
            return new ModelAndView("error");
        }
    }


    @RequestMapping(method = RequestMethod.GET, path = "repSpList")
    public ModelAndView repList(HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            Long ID = Long.parseLong(String.valueOf(session.getAttribute("MyId")));
            ModelAndView modelAndView = new ModelAndView("reports/repListSp");
            modelAndView.addObject("reports", service.findAllById(ID));
            return modelAndView;
        } catch (Exception ex) {
            return new ModelAndView("error");
        }
    }
}
