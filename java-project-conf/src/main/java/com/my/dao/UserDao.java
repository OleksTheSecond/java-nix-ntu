package com.my.dao;

import com.my.constants.UserDaoConstants;
import com.my.domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends AbstractDao {

    public boolean create(Object obj) throws SQLException {
        User user = (User) obj;

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UserDaoConstants.INSERT_USER)){

            preparedStatement.setInt(1, user.getUserId());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setInt(4, user.getRoleId());
            preparedStatement.execute();
            return true;
        }
        catch (SQLException ex) {
           throw new SQLException("UserDao#create");
        }

    }

    public boolean update(Object obj) throws SQLException {
        User user = (User) obj;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UserDaoConstants.UPDATE_USER)){

            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setInt(3, user.getRoleId());
            preparedStatement.setInt(4, user.getUserId());
            preparedStatement.execute();
            return true;
        }
        catch (SQLException e) {
            throw new SQLException("UserDao#update");
        }

    }

    public boolean delete(Object obj) throws SQLException {
        int id = ((User) obj).getUserId();

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UserDaoConstants.DELETE_USER)){

            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            throw new SQLException("UserDao#delete");
        }

    }

    public Object findById(int id) throws SQLException {
        ResultSet resultSet = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UserDaoConstants.FIND_USER)){

            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return mapUser(resultSet);
        }
        catch (SQLException e) {
            throw new SQLException("UserDao#findById");
        }
        finally {
            resultSet.close();
        }

    }

    public List findAll() throws SQLException {
        List<User> userList = new ArrayList<>();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(UserDaoConstants.FIND_ALL)) {

            while (resultSet.next()) {
                userList.add(mapUser(resultSet));
            }
            return userList;
        }
        catch (SQLException e) {
            throw new SQLException("UserDao#findAll");
        }


    }

    private User mapUser(ResultSet resultSet) throws SQLException {
        try {
            User user = new User(resultSet.getInt(UserDaoConstants.USER_ID),
                                 resultSet.getString(UserDaoConstants.FIRST_NAME),
                                 resultSet.getString(UserDaoConstants.LAST_NAME),
                                 resultSet.getInt(UserDaoConstants.ROLE_ID));
            return user;
        }
        catch (SQLException e) {
            throw new SQLException("UserDao#mapUser");
        }

    }
}
