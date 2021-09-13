package com.my.cinema.booking.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *  interface Mapper
 * @param <T>
 */
public interface Mapper<T> {
    T getEntity(ResultSet resultSet) throws SQLException;
}
