package com.my.controller;


import com.my.dao.AbstractDao;
import com.my.dao.ConfDao;
import com.my.domain.Conference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
public class ConferenceController {
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

    @RequestMapping(method = RequestMethod.GET, path = "indexConf")
    public ModelAndView startPage() {
        ModelAndView modelAndView = new ModelAndView("conference/main");
        AbstractDao dao = new ConfDao();
        try {
            List<Conference> conferences = dao.findAll();
            modelAndView.addObject("confs", conferences);

            return modelAndView;
        } catch (Exception e) {
            return new ModelAndView("error");
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = "deleteConf")
    public String delete(@RequestParam(value = "id") String id) {
        Long ID = Long.parseLong(id);
        ConfDao confDao = new ConfDao();
        try {
            confDao.deleteById(ID);
            return "redirect:/indexConf";
        } catch (Exception e) {
            return "error";
        }
    }



    @RequestMapping(method = RequestMethod.GET, path = "addConf")
    public ModelAndView addRep() {
        return new ModelAndView("conference/addConf");
    }

    @RequestMapping(method = RequestMethod.POST, path = "addConfPost")
    public String addRepPost(@RequestParam(value = "place") String place,
                             @RequestParam(value = "startTime") String startTime,
                             @RequestParam (value = "endTime") String endTime) {



        ConfDao confDao = new ConfDao();


        Timestamp start = null;
        Timestamp end = null;
        try {
            start = new Timestamp(dateFormat.parse(startTime).getTime());
            end =  new Timestamp(dateFormat.parse(endTime).getTime());
        } catch (ParseException e) {
            return "error" ;
        }


        if (start.after(end)) {
            return "conference/addConf";
        }

        Conference conference = new Conference(start, end, place);
        try {
            confDao.save(conference);
        } catch (Exception e) {
            return "error" ;
        }

        return "redirect:/indexConf";

    }
}
