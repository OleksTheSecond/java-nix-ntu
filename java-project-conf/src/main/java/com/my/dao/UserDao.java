package com.my.dao;

import com.my.constants.UserDaoConstants;
import com.my.domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends AbstractDao{
    public boolean create(Object obj) {
        User user = (User) obj;

        try (Connection connection = getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(UserDaoConstants.INSERT_USER)){

            int k = 1;
            preparedStatement.setInt(k++, user.getUserId());
            preparedStatement.setString(k++, user.getFirstName());
            preparedStatement.setString(k++, user.getLastName());
            preparedStatement.setInt(k, user.getRoleId());
            preparedStatement.execute();
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }

    public boolean update(Object obj) {
        User user = (User) obj;
        try (Connection connection = getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(UserDaoConstants.UPDATE_USER)){
            int k = 1;
            preparedStatement.setString(k++, user.getFirstName());
            preparedStatement.setString(k++, user.getLastName());
            preparedStatement.setInt(k++, user.getRoleId());
            preparedStatement.setInt(k, user.getUserId());
            preparedStatement.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean delete(Object obj) {
        int id = ((User) obj).getUserId();

        try (Connection connection = getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(UserDaoConstants.DELETE_USER)){

            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Object findById(int id) {
        ResultSet resultSet;
        try (Connection connection = getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(UserDaoConstants.FIND_USER)){

            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return mapUser(resultSet);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List findAll() {
        List<User> userList = new ArrayList<>();
        try (Connection connection = getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(UserDaoConstants.FIND_ALL)) {

            while (resultSet.next()) {
                userList.add(mapUser(resultSet));
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }

    private User mapUser(ResultSet resultSet) {
        try {
            User user = new User(resultSet.getInt(UserDaoConstants.USER_ID),
                                 resultSet.getString(UserDaoConstants.FIRST_NAME),
                                 resultSet.getString(UserDaoConstants.LAST_NAME),
                                 resultSet.getInt(UserDaoConstants.ROLE_ID));
            return user;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
