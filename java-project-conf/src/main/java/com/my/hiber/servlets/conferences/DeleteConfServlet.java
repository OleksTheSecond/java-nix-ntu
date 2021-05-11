package com.my.hiber.servlets.conferences;

import com.my.hiber.dao.ConfDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/hDeleteConf")
public class DeleteConfServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            Long id = Long.parseLong(request.getParameter("id"));
            ConfDao confDao = new ConfDao();
            confDao.deleteById(id);
            response.sendRedirect(request.getContextPath() + "/hIndexConf");
        }
        catch(Exception ex) {
            getServletContext().getRequestDispatcher("/jsp/error.jsp").forward(request, response);
        }
    }
}
