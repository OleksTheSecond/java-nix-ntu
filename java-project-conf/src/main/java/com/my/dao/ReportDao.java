package com.my.dao;


import com.my.constants.ReportDaoConstants;
import com.my.domain.Report;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportDao extends AbstractDao{
    public boolean create(Object obj) {
        Report report = (Report) obj;

        try (Connection connection = getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(ReportDaoConstants.INSERT_REPORT)){

            int k = 1;
            preparedStatement.setInt(k++, report.getId());
            preparedStatement.setString(k++, report.getTitle());
            preparedStatement.setInt(k++, report.getUserId());
            preparedStatement.setInt(k, report.getConferenceId());
            preparedStatement.execute();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean update(Object obj) {
        Report report = (Report) obj;
        try (Connection connection = getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(ReportDaoConstants.UPDATE_REPORT)) {
            int k = 1;
            preparedStatement.setString(k++, report.getTitle());
            preparedStatement.setInt(k++, report.getUserId());
            preparedStatement.setInt(k++, report.getConferenceId());
            preparedStatement.setInt(k, report.getId());
            preparedStatement.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean delete(Object obj) {
        int id = ((Report) obj).getId();
        try (Connection connection = getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(ReportDaoConstants.DELETE_REPORT)) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();

        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Object findById(int id) {
        ResultSet resultSet;
        try(Connection connection = getConnection(URL, USER, PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(ReportDaoConstants.FIND_REPORT)) {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return mapReport(resultSet);
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List findAll() {
        List<Report> reportList = new ArrayList<>();
        try (Connection connection = getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(ReportDaoConstants.FIND_ALL)) {

            while (resultSet.next()) {
                reportList.add(mapReport(resultSet));
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return reportList;
    }

    private Report mapReport(ResultSet resultSet) {
        try {
            Report report = new Report(resultSet.getInt(ReportDaoConstants.REPORT_ID),
                                       resultSet.getString(ReportDaoConstants.REPORT_TITLE),
                                       resultSet.getInt(ReportDaoConstants.USER_ID));
            report.setConferenceId(resultSet.getInt(ReportDaoConstants.CONF_ID));
            return report;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
