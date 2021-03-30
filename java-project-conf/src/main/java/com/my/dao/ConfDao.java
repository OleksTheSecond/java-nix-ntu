package com.my.dao;

import com.my.constants.ConfDaoConstants;
import com.my.domain.Conference;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConfDao extends AbstractDao {
    @Override
    public boolean create(Object obj) {
        Conference conference = (Conference) obj;

        try (Connection connection = getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement =
                     connection.prepareStatement(ConfDaoConstants.INSERT_CONF)) {

            int k = 1;
            preparedStatement.setInt(k++, conference.getConfId());
            preparedStatement.setTimestamp(k++, conference.getStartTime());
            preparedStatement.setTimestamp(k++, conference.getEndTime());
            preparedStatement.setString(k++, conference.getPlace());
            preparedStatement.setInt(k, conference.getReportsCount());
            preparedStatement.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    //В базе Количество докладов должно работать по тригеру
    //добавился доклад с id конференции +1 к количеств там.
    @Override
    public boolean update(Object obj) {
        Conference conference = (Conference) obj;
        try (Connection connection = getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(ConfDaoConstants.UPDATE_CONF)) {
            int k = 1;
            preparedStatement.setTimestamp(k++, conference.getStartTime());
            preparedStatement.setTimestamp(k++, conference.getEndTime());
            preparedStatement.setString(k++, conference.getPlace());
            preparedStatement.setInt(k, conference.getConfId());
            preparedStatement.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public boolean delete(Object obj) {
        int id = ((Conference) obj).getConfId();
        try (Connection connection = getConnection(URL,USER,PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(ConfDaoConstants.DELETE_CONF)) {

            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Object findById(int id) {
        ResultSet resultSet;
        try (Connection connection = getConnection(URL,USER,PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(ConfDaoConstants.FIND_CONF)) {

             preparedStatement.setInt(1, id);
             resultSet = preparedStatement.executeQuery();
             resultSet.next();
             return mapConference(resultSet);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List findAll() {
        List<Conference> conferenceList = new ArrayList<>();
        try (Connection connection = getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(ConfDaoConstants.FIND_ALL)) {

            while (resultSet.next()) {
                conferenceList.add(mapConference(resultSet));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return conferenceList;
    }

    private Conference mapConference(ResultSet resultSet) {
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
            e.printStackTrace();
        }
        return null;
    }
}
