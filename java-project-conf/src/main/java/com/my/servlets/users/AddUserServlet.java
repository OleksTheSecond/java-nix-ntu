package com.my.servlets.users;

import com.my.dao.UserDao;
import com.my.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/addUser")
public class AddUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<User> users = new UserDao().findAll();
            request.setAttribute("users", users);

            getServletContext().getRequestDispatcher("/jsp/users/addUser.jsp").forward(request, response);
        } catch (SQLException ex) {
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
            userDao.create(user);
            response.sendRedirect(request.getContextPath() + "/indexUser");
        } catch (Exception ex) {

            getServletContext().getRequestDispatcher("/jsp/error.jsp").forward(request, response);
        }
    }
}
