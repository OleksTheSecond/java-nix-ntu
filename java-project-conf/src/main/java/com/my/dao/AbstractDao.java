package com.my.dao;

import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class AbstractDao implements Dao {
    private static BasicDataSource ds = new BasicDataSource();
    static {
        ds.setUrl("jdbc:h2:~/conf");
        ds.setUsername("sa");
        ds.setPassword("sa");
        ds.setMinIdle(5);
        ds.setMaxIdle(10);
        ds.setMaxOpenPreparedStatements(100);
    }

    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
