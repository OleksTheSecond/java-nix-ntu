package com.my.dao;

import com.my.constants.ConfDaoConstants;
import com.my.domain.Conference;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConfDao extends AbstractDao {
    @Override
    public boolean create(Object obj) throws SQLException {
        Conference conference = (Conference) obj;

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(ConfDaoConstants.INSERT_CONF)) {

            preparedStatement.setInt(1, conference.getConfId());
            preparedStatement.setTimestamp(2, conference.getStartTime());
            preparedStatement.setTimestamp(3, conference.getEndTime());
            preparedStatement.setString(4, conference.getPlace());
            preparedStatement.setInt(5, conference.getReportsCount());
            preparedStatement.execute();
            return true;
        }
        catch (SQLException e) {
            throw  new SQLException("Exception in ConfDao#create");
        }

    }


    //В базе Количество докладов должно работать по тригеру
    //добавился доклад с id конференции +1 к количеств там.
    @Override
    public boolean update(Object obj) throws SQLException {
        Conference conference = (Conference) obj;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ConfDaoConstants.UPDATE_CONF)) {
            int k = 1;
            preparedStatement.setTimestamp(1, conference.getStartTime());
            preparedStatement.setTimestamp(2, conference.getEndTime());
            preparedStatement.setString(3, conference.getPlace());
            preparedStatement.setInt(4, conference.getConfId());
            preparedStatement.execute();
            return true;
        }
        catch (SQLException e) {
            throw  new SQLException("Exception in ConfDao#update");
        }
    }

    @Override
    public boolean delete(Object obj) throws SQLException{
        int id = ((Conference) obj).getConfId();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ConfDaoConstants.DELETE_CONF)) {

            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            return true;
        }
        catch (SQLException e) {
            throw  new SQLException("Exception in ConfDao#delete");
        }

    }

    @Override
    public Object findById(int id) throws SQLException {
        ResultSet resultSet = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ConfDaoConstants.FIND_CONF)) {

             preparedStatement.setInt(1, id);
             resultSet = preparedStatement.executeQuery();
             resultSet.next();
             return mapConference(resultSet);
        }
        catch (SQLException e) {
            throw  new SQLException("Exception in ConfDao#findById");
        }
        finally {
            resultSet.close();
        }
    }

    @Override
    public List findAll() throws SQLException {
        List<Conference> conferenceList = new ArrayList<>();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(ConfDaoConstants.FIND_ALL)) {

            while (resultSet.next()) {
                conferenceList.add(mapConference(resultSet));
            }

            return conferenceList;
        }
        catch (SQLException e) {
            throw  new SQLException("Exception in ConfDao#findAll");
        }
    }

    private Conference mapConference(ResultSet resultSet) throws SQLException {
        try {
            Conference conference
                    = new Conference(resultSet.getInt(ConfDaoConstants.CONF_ID),
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
