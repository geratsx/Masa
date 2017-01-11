package com.alexey.mvc;

import java.util.Collection;

/**
 * DAO - data access object
 * DAL - data access layer
 * @param <T>
 */
public interface DAO<T> {

    T create (T t);

    boolean delete(Integer id);

    T select (Integer id);

    T update (T t);

    Collection<T> selectAll();

}
