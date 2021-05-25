package com.my.controller;
import com.my.dao.UserDao;
import com.my.domain.User;
import com.my.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {
    private UserService service = new UserService();

    @RequestMapping(method = RequestMethod.GET, path = "backToProfile")
    public ModelAndView backToProfile(HttpServletRequest request) {

        HttpSession session = request.getSession();

        Long ID = Long.parseLong(String.valueOf(session.getAttribute("MyId")));


        try {
            User user = service.getUser(ID);
            ModelAndView model = mainPage(user);
            model.addObject("user", user);
            return model;
        } catch (Exception e) {
            return new ModelAndView("error");
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "userList")
    public ModelAndView userList(@RequestParam(value = "id") String id) {
        try {
            List<User> users = service.findAll();
            ModelAndView modelAndView = new ModelAndView("users/userList");
            modelAndView.addObject("MyId", id);
            modelAndView.addObject("users", users);
            return modelAndView;
        } catch (Exception e) {
            return new ModelAndView("error");
        }
    }


    @RequestMapping(method = RequestMethod.GET, path = "loginRedirect")
    public String loginRedirect() {
        return "users/login";
    }

    @RequestMapping(method = RequestMethod.GET, path = "regRedirect")
    public String regRedirect() {
        return "users/registration";
    }

    @RequestMapping(method = RequestMethod.POST, path = "reg")
    public ModelAndView registration(@RequestParam(value = "firstName") String firstName,
                                     @RequestParam(value = "lastName") String lastName,
                                     @RequestParam(value = "roleId") String roleId,
                                     @RequestParam(value = "email")String email,
                                     @RequestParam(value = "password") String password)
    {
        String check = service.userCheck(firstName, lastName, email, password);
        if (check.equals("OK")) {
            int role = Integer.parseInt(roleId);
            try {
                service.addUser(firstName, lastName, email, password, role);
                return new ModelAndView("users/login");
            } catch (Exception e) {
                return new ModelAndView("error");
            }
        }
        else {
            ModelAndView modelAndView = new ModelAndView("users/registration");
            modelAndView.addObject("error", check);
            modelAndView.addObject("firstName", firstName);
            modelAndView.addObject("lastName", lastName);
            modelAndView.addObject("email" , email);
            return modelAndView;
        }
    }



    @RequestMapping(method = RequestMethod.GET, path = "updateUser")
    public ModelAndView updateUser(@RequestParam(value = "id") String id) {
        Long ID = Long.parseLong(id);
        User user = null;
        try {
            user = service.getUser(ID);
            ModelAndView model = new ModelAndView("users/updateProfile");
            model.addObject("user", user);
            return model;
        } catch (Exception e) {
            return new ModelAndView("error");
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = "updateUserPost")
    public ModelAndView updateUser(@RequestParam(value = "firstName") String firstName,
                             @RequestParam(value = "lastName") String lastName,
                             @RequestParam(value = "email") String email) {
        User user = null;
        try {
            user = service.getUser(email);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            service.updateUser(user);

            ModelAndView model = mainPage(user);
            model.addObject("user", user);
            return model;
        } catch (Exception e) {
            return new ModelAndView("error");
        }

    }


    @RequestMapping(method = RequestMethod.POST, path = "login")
    public ModelAndView login(HttpServletRequest request,
                                @RequestParam(value = "email") String email,
                              @RequestParam(value = "password") String password) {

        ModelAndView model;
        String check = null;
        try {
            check = service.login(email, password);
        } catch (Exception e) {
            return new ModelAndView("error");
        }
        if (check.equals("OK")) {
            User user = null;
            try {
                user = service.getUser(email);
                model = mainPage(user);
                HttpSession sess = request.getSession(true);
                sess.setAttribute("MyId", user.getId());
                model.addObject("user", user);
                return model;
            } catch (Exception e) {
                return new ModelAndView("error");
            }


        } else {
            model = new ModelAndView("users/login");
            model.addObject("error", check);
            return model;
        }
    }




    @RequestMapping(method = RequestMethod.POST, path = "addUsPost")
    public String addRepPost(@RequestParam(value = "firstName") String firstName,
                             @RequestParam(value = "lastName") String lastName,
                             @RequestParam(value = "roleId") String roleId,
                             @RequestParam(value = "email") String email,
                             @RequestParam(value = "password") String password) {

        int roleID = Integer.parseInt(roleId);
        User user = new User(firstName, lastName, email, password, roleID);
        UserDao userDao = new UserDao();
        try {
            userDao.save(user);
        } catch (Exception e) {
            return "error";
        }

        return "redirect:/indexUs";

    }

    private ModelAndView mainPage(User user) {
        if (user.getRoleId() == 1)
            return new ModelAndView("users/mainModer");
        else if (user.getRoleId() == 2)
            return new ModelAndView("users/mainSpeaker");
        else if (user.getRoleId() == 3)
            return new ModelAndView("users/mainListener");
        return new ModelAndView("error");
    }
}
