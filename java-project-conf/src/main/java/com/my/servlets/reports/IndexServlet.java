package com.my.servlets.reports;

import com.my.dao.ReportDao;
import com.my.domain.Report;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/indexReport")
public class IndexServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            List<Report> reports = new ReportDao().findAll();
            request.setAttribute("reports", reports);

            getServletContext().getRequestDispatcher("/jsp/reports/main.jsp").forward(request, response);
        }
        catch (SQLException ex) {
            getServletContext().getRequestDispatcher("/jsp/error.jsp").forward(request, response);
        }

    }
}
