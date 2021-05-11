package com.my.hiber.servlets.users;

import com.my.domain.User;
import com.my.hiber.dao.AbstractDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/hIndexUser")
public class IndexServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            AbstractDao dao = new com.my.hiber.dao.UserDao();
            List<User> users = dao.findAll();
            request.setAttribute("users", users);

            getServletContext().getRequestDispatcher("/jsp/users/main.jsp").forward(request, response);
        }
         catch (Exception e) {
            getServletContext().getRequestDispatcher("/jsp/error.jsp").forward(request, response);
        }

    }
}
