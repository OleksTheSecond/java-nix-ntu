package com.my.dao;

import java.util.List;

public interface Dao<T> {
     boolean create(T obj);
     boolean update(T obj);
     boolean delete(T obj);
     T findById(int id);
     List<T> findAll();
}
