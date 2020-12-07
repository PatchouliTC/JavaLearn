package com.cb.dao;

import java.util.List;

public interface BaseDao<T> {

    T get(long id);

    List<T> findAll();

    int insert(T entity);

    int update(T entity);

    int delete(long[] ids);

}