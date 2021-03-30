package com.my.constants;

public interface UserDaoConstants {
    String USER_ID = "user_id";
    String FIRST_NAME = "first_name";
    String LAST_NAME = "last_name";
    String ROLE_ID = "role_id";


    String INSERT_USER = "INSERT INTO Users VALUES (?,?,?,?)";
    String DELETE_USER = "DELETE FROM Users WHERE user_id = ?";
    String FIND_USER = "SELECT * FROM Users WHERE user_id = ?";
    String FIND_ALL = "SELECT * FROM Users";
    String UPDATE_USER =
            "UPDATE USERS SET first_name = ?, last_name = ?, role_id = ? WHERE user_id = ?";
}
