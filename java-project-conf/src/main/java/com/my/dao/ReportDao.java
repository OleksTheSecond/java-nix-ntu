package com.my.dao;


import com.my.constants.ReportDaoConstants;
import com.my.domain.Report;
import jdk.javadoc.doclet.Reporter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportDao extends AbstractDao<Report>{

    @Override
    protected String getFindByIdSql() {
        return ReportDaoConstants.FIND_REPORT;
    }

    @Override
    protected String getCreateSql() {
        return ReportDaoConstants.INSERT_REPORT;
    }

    @Override
    protected String getUpdateSql() {
        return ReportDaoConstants.UPDATE_REPORT;
    }

    @Override
    protected String getDeleteSql() {
        return ReportDaoConstants.DELETE_REPORT;
    }

    @Override
    protected String getFindAllSql() {
        return ReportDaoConstants.FIND_ALL;
    }


    @Override
    protected EntityMapper<Report> getEntityMapper() throws SQLException {
        return new EntityMapper<Report>() {

            @Override
            public Report fromResultSet(ResultSet resultSet) throws SQLException {
                return mapReport(resultSet);
            }

            @Override
            public void fillCreateStatement(PreparedStatement statement,
                                            Report entity) throws SQLException {
                statement = fillStatement(statement,entity);
            }

            @Override
            public void fillUpdateStatement(PreparedStatement statement, Report entity) throws SQLException {
                statement = fillStatement(statement,entity);
                statement.setLong(4, entity.getId());
            }

            private PreparedStatement fillStatement(PreparedStatement st, Report entity) throws SQLException {
                st.setString(1, entity.getTitle());
                st.setLong(2, entity.getUserId());
                st.setLong(3, entity.getConferenceId());
                return st;
            }
        };
    }

    private Report mapReport(ResultSet resultSet) throws SQLException {
        try {
            Report report = new Report(
                    resultSet.getString(ReportDaoConstants.REPORT_TITLE),
                    resultSet.getLong(ReportDaoConstants.USER_ID));
            report.setConferenceId(resultSet.getLong(ReportDaoConstants.CONF_ID));
            report.setId(resultSet.getLong(ReportDaoConstants.REPORT_ID));
            return report;
        } catch (SQLException e) {
            throw new SQLException("Exception in ReportDao#mapReport");
        }
    }
}
