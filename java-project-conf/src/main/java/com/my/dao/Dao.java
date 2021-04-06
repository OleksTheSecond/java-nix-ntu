package com.my.dao;

import java.sql.SQLException;
import java.util.List;

public interface Dao<T> {
     boolean create(T obj) throws SQLException;
     boolean update(T obj) throws SQLException;
     boolean delete(T obj) throws SQLException;
     T findById(int id) throws SQLException;
     List<T> findAll() throws SQLException;
}
