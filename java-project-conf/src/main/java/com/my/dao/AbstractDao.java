package com.my.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class AbstractDao implements Dao {
    public static final String URL = "jdbc:h2:~/conf";
    public static final String USER = "sa";
    public static final String PASSWORD = "sa";

    public Connection getConnection(String connectionUrl, String user, String password) throws SQLException {
        return DriverManager.getConnection(connectionUrl, user, password);
    }
}
