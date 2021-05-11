package com.my.servlets.reports;

import com.my.dao.AbstractDao;
import com.my.dao.ReportDao;
import com.my.domain.Report;

import javax.persistence.SqlResultSetMapping;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/printReports")
public class PrintReportsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        StringBuilder stb = new StringBuilder();
        ReportDao reportDao = new ReportDao();
        List<Report> list = null;
        try {
            list = reportDao.findAll();
        }
        catch (SQLException ex) {
            String path = req.getContextPath() + "/jsp/error.jsp";
            resp.sendRedirect(path);
        }

        String[] rep = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            stb = new StringBuilder();
            stb.append((i+1)+"#  ");
            stb.append("Title = ");
            stb.append(list.get(i).getTitle());
            stb.append("    Conference id  = ");
            stb.append(list.get(i).getConferenceId());
            stb.append("   Speaker id  = ");
            stb.append(list.get(i).getUserId());
            rep[i] = stb.toString();
        }

        req.setAttribute("reports", rep);
        getServletContext().getRequestDispatcher("/jsp/reports/printReports.jsp").forward(req, resp);

    }
}
