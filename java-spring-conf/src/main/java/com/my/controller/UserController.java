package com.my.controller;

import com.my.dao.AbstractDao;
import com.my.dao.UserDao;
import com.my.domain.Conference;
import com.my.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {

    @RequestMapping(method = RequestMethod.GET, path = "indexUs")
    public ModelAndView startPage() {
        ModelAndView modelAndView = new ModelAndView("users/main");
        try {
            AbstractDao dao = new UserDao();
            List<User> users = dao.findAll();
            modelAndView.addObject("users", users);
            return modelAndView;

        } catch (Exception e) {
                return new ModelAndView("error");
        }
    }


    @RequestMapping(method = RequestMethod.POST, path = "deleteUs")
    public String delete(@RequestParam(value = "id") String id) {
        Long ID = Long.parseLong(id);
        UserDao userDao = new UserDao();
        try {
            userDao.deleteById(ID);
        } catch (Exception e) {
            return "error";
        }
        return "redirect:/indexUs";
    }



    @RequestMapping(method = RequestMethod.GET, path = "addUs")
    public ModelAndView addRep() throws Exception {
        ModelAndView model = new ModelAndView("users/addUser");
        List<User> users = new UserDao().findAll();
        model.addObject("users", users);
        return model;
    }

    @RequestMapping(method = RequestMethod.POST, path = "addUsPost")
    public String addRepPost(@RequestParam(value = "firstName") String firstName,
                             @RequestParam(value = "lastName") String lastName,
                             @RequestParam (value = "roleId") String roleId) {

        int roleID = Integer.parseInt(roleId);
        User user = new User(firstName, lastName, roleID);
        UserDao userDao = new UserDao();
        try {
            userDao.save(user);
        } catch (Exception e) {
            return "error";
        }

        return "redirect:/indexUs";

    }


}
