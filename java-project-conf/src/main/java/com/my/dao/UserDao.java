package com.my.dao;

import com.my.constants.UserDaoConstants;
import com.my.domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends AbstractDao<User>{

    @Override
    protected String getFindByIdSql() {
        return UserDaoConstants.FIND_USER;
    }

    @Override
    protected String getCreateSql() {
        return UserDaoConstants.INSERT_USER;
    }

    @Override
    protected String getUpdateSql() {
        return UserDaoConstants.UPDATE_USER;
    }

    @Override
    protected String getDeleteSql() {
        return UserDaoConstants.DELETE_USER;
    }

    @Override
    protected String getFindAllSql() {
        return UserDaoConstants.FIND_ALL;
    }


    @Override
    protected EntityMapper<User> getEntityMapper() throws SQLException {
        return new EntityMapper<User>() {

            @Override
            public User fromResultSet(ResultSet resultSet) throws SQLException {
                return mapUser(resultSet);
            }

            @Override
            public void fillCreateStatement(PreparedStatement statement,
                                            User entity) throws SQLException {
                statement = fillStatement(statement,entity);
            }

            @Override
            public void fillUpdateStatement(PreparedStatement statement, User entity) throws SQLException {
                statement = fillStatement(statement,entity);
                statement.setLong(4, entity.getId());
            }

            private PreparedStatement fillStatement(PreparedStatement st, User entity) throws SQLException {
                st.setString(1, entity.getFirstName());
                st.setString(2, entity.getLastName());
                st.setInt(3, entity.getRoleId());
                return st;
            }
        };
    }

    private User mapUser(ResultSet resultSet) throws SQLException {
        try {
            User user = new User(
                                 resultSet.getString(UserDaoConstants.FIRST_NAME),
                                 resultSet.getString(UserDaoConstants.LAST_NAME),
                                 resultSet.getInt(UserDaoConstants.ROLE_ID));
            user.setId(resultSet.getLong(UserDaoConstants.USER_ID));
            return user;
        }
        catch (SQLException e) {
            throw new SQLException("UserDao#mapUser");
        }

    }
}
