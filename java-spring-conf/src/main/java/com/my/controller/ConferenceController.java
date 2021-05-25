package com.my.controller;

import com.my.domain.Conference;
import com.my.services.ConferenceService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;

@Controller
public class ConferenceController {

    private ConferenceService service = new ConferenceService();


    @RequestMapping(method = RequestMethod.GET, path = "addConf")
    public ModelAndView addConf() {
        return new ModelAndView("conference/addConf");
    }

    @RequestMapping(method = RequestMethod.POST, path = "addConfPost")
    public ModelAndView addConfPost(
            @RequestParam(value = "place") String place,
            @RequestParam(value = "startTime") String startTime,
            @RequestParam(value = "endTime") String endTime) {

        if (!service.confCheck(place).equals("OK")) {
            ModelAndView model = addConf();
            model.addObject("error", service.confCheck(place));
            return model;
        }


        Timestamp start = null;
        Timestamp end = null;
        try {
            start = service.timeParser(startTime);
            end = service.timeParser(endTime);
        } catch (ParseException e) {
            return new ModelAndView("error");
        }


        if (start.after(end)) {
            return new ModelAndView("conference/addConf");
        }

        Conference conference = new Conference(start, end, place);
        try {
            service.save(conference);
        } catch (Exception e) {
            return new ModelAndView("error");
        }


        return new ModelAndView("redirect:/backToProfile");

    }

    @RequestMapping(method = RequestMethod.GET, path = "confList")
    public ModelAndView confList() {
        try {
            List<Conference> list = service.findAll();
            ModelAndView model = new ModelAndView("conference/confList");
            model.addObject("confs", list);
            return model;
        } catch (Exception e) {
            return new ModelAndView("error");
        }
    }


}
