package com.my.cinema.booking.dao.mapper;

import com.my.cinema.booking.model.entity.Seat;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SeatMapper implements Mapper<Seat> {
    @Override
    public Seat getEntity(ResultSet resultSet) throws SQLException {
        Seat seat = new Seat();
        seat.setRowId(resultSet.getInt("row_id"));
        seat.setNumber(resultSet.getInt("number"));
        seat.setId(resultSet.getLong("id"));
       return seat;
    }
}
