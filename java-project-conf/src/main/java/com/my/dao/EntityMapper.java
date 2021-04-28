package com.my.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface EntityMapper<E> {

    E fromResultSet(ResultSet resultSet) throws SQLException;

    void fillCreateStatement(PreparedStatement statement, E entity) throws SQLException;

    void fillUpdateStatement(PreparedStatement statement, E entity) throws SQLException;


}
