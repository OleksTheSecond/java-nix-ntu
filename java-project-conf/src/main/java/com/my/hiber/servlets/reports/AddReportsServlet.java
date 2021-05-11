package com.my.hiber.servlets.reports;

import com.my.domain.Conference;
import com.my.domain.Report;
import com.my.domain.User;
import com.my.hiber.dao.ConfDao;
import com.my.hiber.dao.ReportDao;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/hAddReport")
public class AddReportsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<User> users = new com.my.hiber.dao.UserDao().findAllSpeakers();
            request.setAttribute("user", users);
            List<Conference> conferenceList = new com.my.hiber.dao.ConfDao().findAll();
            request.setAttribute("conf", conferenceList);

            getServletContext().getRequestDispatcher("/jsp/reports/AddReport.jsp").forward(request, response);
        } catch (Exception ex) {
            getServletContext().getRequestDispatcher("/jsp/error.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String title = request.getParameter("title");
            long userId = Integer.parseInt(request.getParameter("userId"));
            long confId = Integer.parseInt(request.getParameter("confId"));
            Report report = new Report(title, userId);
            report.setConferenceId(confId);
            ReportDao reportDao = new ReportDao();
            reportDao.save(report);
            ConfDao confDao = new ConfDao();
            Conference conference = confDao.findById(confId);
            conference.setReportsCount(conference.getReportsCount()+1);
            confDao.update(conference);
            response.sendRedirect(request.getContextPath() + "/hIndexReport");
        } catch (Exception ex) {
            getServletContext().getRequestDispatcher("/jsp/error.jsp").forward(request, response);
        }
    }
}
