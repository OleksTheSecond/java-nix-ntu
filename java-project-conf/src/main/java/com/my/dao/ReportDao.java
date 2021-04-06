package com.my.dao;


import com.my.constants.ReportDaoConstants;
import com.my.domain.Report;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportDao extends AbstractDao{

    public boolean create(Object obj) throws SQLException {
        Report report = (Report) obj;

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(ReportDaoConstants.INSERT_REPORT)){

            preparedStatement.setInt(1, report.getId());
            preparedStatement.setString(2, report.getTitle());
            preparedStatement.setInt(3, report.getUserId());
            preparedStatement.setInt(4, report.getConferenceId());
            preparedStatement.execute();
            return true;
        }
        catch (SQLException ex) {
           throw new SQLException("Exception in ReportDao#create");

        }

    }

    public boolean update(Object obj) throws SQLException {
        Report report = (Report) obj;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(ReportDaoConstants.UPDATE_REPORT)) {

            preparedStatement.setString(1, report.getTitle());
            preparedStatement.setInt(2, report.getUserId());
            preparedStatement.setInt(3, report.getConferenceId());
            preparedStatement.setInt(4, report.getId());
            preparedStatement.execute();
            return true;
        }
        catch (SQLException e) {
            throw new SQLException("Exception in ReportDao#update");
        }

    }

    public boolean delete(Object obj) throws SQLException {
        int id = ((Report) obj).getId();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(ReportDaoConstants.DELETE_REPORT)) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            return true;
        }
        catch (SQLException e) {
            throw new SQLException("Exception in ReportDao#delete");
        }

    }

    public Object findById(int id) throws SQLException {
        ResultSet resultSet = null;
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(ReportDaoConstants.FIND_REPORT)) {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return mapReport(resultSet);
        }
        catch (SQLException e) {
            throw new SQLException("Exception in ReportDao#findById");
        }
        finally {
            resultSet.close();
        }
    }

    public List findAll() throws SQLException {
        List<Report> reportList = new ArrayList<>();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(ReportDaoConstants.FIND_ALL)) {

            while (resultSet.next()) {
                reportList.add(mapReport(resultSet));
            }

            return reportList;
        }
        catch (SQLException e) {
            throw new SQLException("Exception in ReportDao#findAll");
        }
    }

    private Report mapReport(ResultSet resultSet) throws SQLException {
        try {
            Report report = new Report(resultSet.getInt(ReportDaoConstants.REPORT_ID),
                                       resultSet.getString(ReportDaoConstants.REPORT_TITLE),
                                       resultSet.getInt(ReportDaoConstants.USER_ID));
            report.setConferenceId(resultSet.getInt(ReportDaoConstants.CONF_ID));
            return report;
        }
        catch (SQLException e) {
            throw new SQLException("Exception in ReportDao#mapReport");
        }
    }
}
