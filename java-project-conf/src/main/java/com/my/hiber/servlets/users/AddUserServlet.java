package com.my.hiber.servlets.users;


import com.my.domain.User;
import com.my.hiber.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/hAddUser")
public class AddUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<User> users = new UserDao().findAll();
            request.setAttribute("users", users);

            getServletContext().getRequestDispatcher("/jsp/users/addUser.jsp").forward(request, response);
        } catch (Exception ex) {
            getServletContext().getRequestDispatcher("/jsp/error.jsp").forward(request, response);
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            int roleId = Integer.parseInt(request.getParameter("roleId"));
            User user = new User(firstName, lastName, roleId);
            UserDao userDao = new UserDao();
            userDao.save(user);
            response.sendRedirect(request.getContextPath() + "/hIndexUser");
        } catch (Exception ex) {

            getServletContext().getRequestDispatcher("/jsp/error.jsp").forward(request, response);
        }
    }
}
