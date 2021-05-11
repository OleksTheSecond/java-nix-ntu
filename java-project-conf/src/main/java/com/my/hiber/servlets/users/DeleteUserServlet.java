package com.my.hiber.servlets.users;

import com.my.hiber.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/hDeleteUser")
public class DeleteUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            Long id = Long.parseLong(request.getParameter("id"));
            UserDao userDao = new UserDao();
            if(userDao.deleteById(id))
                response.sendRedirect(request.getContextPath() + "/hIndexUser");
            else
                throw new Exception();
        }
        catch(Exception ex) {
            getServletContext().getRequestDispatcher("/jsp/error.jsp").forward(request, response);
        }
    }
}
