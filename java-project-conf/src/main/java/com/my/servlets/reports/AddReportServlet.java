package com.my.servlets.reports;

import com.my.dao.ReportDao;
import com.my.dao.UserDao;
import com.my.domain.Report;
import com.my.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/addReport")
public class AddReportServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<User> users = new UserDao().findAll();
            request.setAttribute("users", users);

            getServletContext().getRequestDispatcher("/jsp/reports/AddReport.jsp").forward(request, response);
        } catch (SQLException ex) {
            getServletContext().getRequestDispatcher("/jsp/error.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String title = request.getParameter("title");
            long userId = Integer.parseInt(request.getParameter("userId"));
            Report report = new Report(title, userId);
            ReportDao reportDao = new ReportDao();
            reportDao.create(report);
            response.sendRedirect(request.getContextPath() + "/indexReport");
        } catch (Exception ex) {

            getServletContext().getRequestDispatcher("/jsp/error.jsp").forward(request, response);
        }
    }
}
