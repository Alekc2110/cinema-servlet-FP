package com.my.cinema.booking.dao.mapper;

import com.my.cinema.booking.model.entity.MovieSession;
import com.my.cinema.booking.model.entity.Row;
import com.my.cinema.booking.model.entity.Seat;
import com.my.cinema.booking.model.entity.Ticket;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class TicketMapper implements Mapper<Ticket> {

    @Override
    public Ticket getEntity(ResultSet resultSet) throws SQLException {
        return new Ticket.Builder().
                setMovieSession(getMovieSession(resultSet)).
                setRow(getRow(resultSet)).
                setSeat(getSeat(resultSet)).
                setOrderId(resultSet.getLong("order_id")).
                build();
    }

    private Row getRow(ResultSet rs) throws SQLException {
        Row row = new Row();
        row.setId(rs.getLong("r.id"));
        row.setNumber(rs.getInt("r.number"));
        return row;
    }


    private MovieSession getMovieSession(ResultSet rs) throws SQLException {
        MovieSession movieSession = new MovieSession.Builder()
                .setMovieId(Long.parseLong(rs.getString("movie_id")))
                .setDate(rs.getDate("show_date").toLocalDate())
                .setTime(rs.getTime("show_time").toLocalTime())
                .setTicketPrice(Integer.parseInt(rs.getString("ticket_price")))
                .build();
        movieSession.setId(rs.getLong("ms.id"));
        return movieSession;
    }

    private Seat getSeat(ResultSet rs) throws SQLException {
        Seat seat = new Seat();
        seat.setRowId(rs.getLong("s.row_id"));
        seat.setNumber(rs.getInt("s.number"));
        seat.setId(rs.getLong("s.id"));
        return seat;
    }


}
