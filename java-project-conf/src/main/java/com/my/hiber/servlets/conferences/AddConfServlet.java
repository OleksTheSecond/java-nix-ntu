package com.my.hiber.servlets.conferences;

import com.my.domain.Conference;
import com.my.hiber.dao.ConfDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/hAddConf")
public class AddConfServlet extends HttpServlet {

    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            getServletContext().getRequestDispatcher("/jsp/conference/addConf.jsp").forward(request, response);
        } catch (Exception ex) {
            getServletContext().getRequestDispatcher("/jsp/error.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            ConfDao confDao = new ConfDao();
            String place = request.getParameter("place");
            Date startTime = null;
            Date endTime = null;

            startTime = dateFormat.parse(request.getParameter("startTime"));
            endTime = dateFormat.parse(request.getParameter("endTime"));


            Timestamp start = new Timestamp(startTime.getTime());
            Timestamp end = new Timestamp(endTime.getTime());

            if (start.after(end)) {
                getServletContext().getRequestDispatcher("/jsp/conference/addConf.jsp").forward(request, response);
            }

            Conference conference = new Conference(start, end, place);
            confDao.save(conference);
            response.sendRedirect(request.getContextPath() + "/hIndexConf");
        } catch (Exception ex) {
            getServletContext().getRequestDispatcher("/jsp/error.jsp").forward(request, response);
        }
    }
}
