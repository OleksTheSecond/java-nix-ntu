package com.my.hiber.servlets.reports;

import com.my.domain.Report;
import com.my.domain.User;
import com.my.hiber.dao.AbstractDao;
import com.my.hiber.dao.ReportDao;
import com.my.hiber.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/hIndexReport")
public class IndexServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            AbstractDao dao = new ReportDao();
            List<Report> reports =   ((ReportDao) dao).findAll();
            dao = new UserDao();
            List<User> users = ((UserDao) dao).findAll();

            List<String> speakersWithRep = new ArrayList<>();
            List<Long> repWithSpeakers = new ArrayList<>();
            for (Report report1 : reports) {
                for (User user : users) {

                    if (report1.getUserId().equals(user.getId())) {
                        speakersWithRep.add(
                                user.getFirstName() + " " + user.getLastName());
                        repWithSpeakers.add(report1.getId());
                        break;
                    }
                }
            }

            List<String> titles = new ArrayList<>();
            for (Report report : reports) {
                titles.add(report.getTitle());
            }

            request.setAttribute("titles", titles);
            request.setAttribute("speakersWithRep", speakersWithRep);
            request.setAttribute("repWithSpeakers", repWithSpeakers);
            request.setAttribute("end", titles.size()-1);

            getServletContext().getRequestDispatcher("/jsp/reports/main.jsp").forward(request, response);
        }
        catch (Exception ex) {
            getServletContext().getRequestDispatcher("/jsp/error.jsp").forward(request, response);
        }

    }
}
