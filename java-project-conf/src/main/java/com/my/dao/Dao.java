package com.my.dao;

import java.sql.SQLException;
import java.util.List;

public interface Dao<E> {
     boolean create(E obj) throws SQLException;
     boolean update(E obj) throws SQLException;
     boolean deleteById(Long id) throws SQLException;
     E findById(Long id) throws SQLException;
     List<E> findAll() throws SQLException;
}
