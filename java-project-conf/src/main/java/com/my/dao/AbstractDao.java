package com.my.dao;

import com.my.domain.Conference;
import com.my.domain.Entity;
import org.h2.jdbcx.JdbcConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDao<E extends Entity> implements Dao<E> {
    JdbcConnectionPool cp = JdbcConnectionPool.create(
            "jdbc:h2:~/conf", "sa", "sa");



    public Connection getConnection() throws SQLException {
        return cp.getConnection();
    }

    protected abstract String getUpdateSql();

    protected abstract String getDeleteSql();

    protected abstract String getCreateSql();

    protected abstract String getFindByIdSql();

    protected abstract String getFindAllSql();

    protected abstract EntityMapper<E> getEntityMapper() throws SQLException;

    @Override
    public E findById(Long id) throws SQLException{
        try (Connection con = getConnection(); PreparedStatement st = con.prepareStatement(getFindByIdSql())) {
            st.setLong(1, id);
            ResultSet resultSet = st.executeQuery();
            if (resultSet.next()) {
                EntityMapper<E> entityMapper = getEntityMapper();
                return entityMapper.fromResultSet(resultSet);
            }
        }
        return null;
    }
    @Override
    public boolean deleteById(Long id) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getDeleteSql())) {
            preparedStatement.setLong(1,id);
            preparedStatement.execute();
            return true;
        }
    }

    @Override
    public List<E> findAll() throws SQLException {
        try (Connection con = getConnection();
             Statement st  = con.createStatement();
             ResultSet resultSet = st.executeQuery(getFindAllSql())) {

            List<E> array = new ArrayList<>();
            while (resultSet.next()) {
                EntityMapper<E> entityMapper = getEntityMapper();
                array.add(entityMapper.fromResultSet(resultSet));
            }
            return array;
        }
    }

    @Override
    public boolean create(E entity) throws SQLException {
        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(getCreateSql(),
                     Statement.RETURN_GENERATED_KEYS)) {

            getEntityMapper().fillCreateStatement(st, entity);
            st.executeUpdate();
            return true;
        }
    }

    @Override
    public boolean update(E entity) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getUpdateSql())) {

            getEntityMapper().fillUpdateStatement(preparedStatement, entity);
            preparedStatement.executeUpdate();
            return true;
        }
    }
}
