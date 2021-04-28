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

@WebServlet("/printUser")
public class PrintUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        StringBuilder stb = new StringBuilder();
        UserDao userDao = new UserDao();
        List<User> list = null;
        try {
            list = userDao.findAll();
        }
        catch (SQLException ex) {
            String path = req.getContextPath() + "/jsp/error.jsp";
            resp.sendRedirect(path);
        }

        String[] rep = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            stb = new StringBuilder();
            stb.append((i+1)+"#  ");
            stb.append("First name = ");
            stb.append(list.get(i).getFirstName());
            stb.append("Last name = ");
            stb.append(list.get(i).getLastName());
            stb.append("    Role id  = ");
            stb.append(list.get(i).getRoleId());
            rep[i] = stb.toString();
        }

        req.setAttribute("users", rep);
        getServletContext().getRequestDispatcher("/jsp/users/printUser.jsp").forward(req, resp);

    }
}
