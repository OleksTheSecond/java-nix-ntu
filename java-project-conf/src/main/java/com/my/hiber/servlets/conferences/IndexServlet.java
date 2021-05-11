package com.my.hiber.servlets.conferences;

import com.my.domain.Conference;
import com.my.hiber.dao.AbstractDao;
import com.my.hiber.dao.ConfDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/hIndexConf")
public class IndexServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            AbstractDao dao = new ConfDao();
            List<Conference> conferences = dao.findAll();

            request.setAttribute("confs", conferences);

            getServletContext().getRequestDispatcher("/jsp/conference/main.jsp").forward(request, response);
        }
        catch (Exception e) {
            getServletContext().getRequestDispatcher("/jsp/error.jsp").forward(request, response);
        }

    }
}
