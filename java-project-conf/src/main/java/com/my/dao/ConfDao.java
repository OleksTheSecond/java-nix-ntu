package com.my.dao;

import com.my.constants.ConfDaoConstants;
import com.my.domain.Conference;

import java.sql.*;

public class ConfDao extends AbstractDao<Conference> {

    @Override
    protected String getFindByIdSql() {
        return ConfDaoConstants.FIND_CONF;
    }
    @Override
    protected String getCreateSql() {
        return ConfDaoConstants.INSERT_CONF;
    }

    @Override
    protected String getUpdateSql() {
        return ConfDaoConstants.UPDATE_CONF;
    }

    @Override
    protected String getDeleteSql() {
        return ConfDaoConstants.DELETE_CONF;
    }

    @Override
    protected String getFindAllSql() {
        return ConfDaoConstants.FIND_ALL;
    }

    @Override
    protected EntityMapper<Conference> getEntityMapper() throws SQLException {
        return new EntityMapper<>() {

            @Override
            public Conference fromResultSet(ResultSet resultSet) throws SQLException {
                return mapConference(resultSet);
            }

            @Override
            public void fillCreateStatement(PreparedStatement statement,
                                      Conference entity) throws SQLException{
                statement.setTimestamp(1, entity.getStartTime());
                statement.setTimestamp(2, entity.getEndTime());
                statement.setString(3, entity.getPlace());
                statement.setLong(4, entity.getReportsCount());
            }

            @Override
            public void fillUpdateStatement(PreparedStatement statement, Conference entity) throws SQLException {
                statement.setTimestamp(1, entity.getStartTime());
                statement.setTimestamp(2, entity.getEndTime());
                statement.setString(3, entity.getPlace());
                statement.setLong(4, entity.getId());
            }
        };
    }


    private Conference mapConference(ResultSet resultSet) throws SQLException {
        try {
            Conference conference
                    = new Conference(resultSet.getLong(ConfDaoConstants.CONF_ID),
                                                   resultSet.getTimestamp(ConfDaoConstants.START_TIME),
                                                   resultSet.getTimestamp(ConfDaoConstants.END_TIME),
                                                   resultSet.getString(ConfDaoConstants.PLACE),
                                                   resultSet.getInt(ConfDaoConstants.RPEORTS_COUNT));
            return conference;
        }
        catch (SQLException e) {
            throw  new SQLException("Exception in ConfDao#mapConference");
        }
    }
}
