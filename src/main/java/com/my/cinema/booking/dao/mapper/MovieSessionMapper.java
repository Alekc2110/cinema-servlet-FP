package com.my.cinema.booking.dao.mapper;

import com.my.cinema.booking.model.entity.MovieSession;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class MovieSessionMapper implements Mapper<MovieSession> {
    @Override
    public MovieSession getEntity(ResultSet resultSet) throws SQLException {
        MovieSession movieSession = new MovieSession.Builder()
                .setMovieId(resultSet.getLong("movie_id"))
                .setDate(resultSet.getDate("show_date").toLocalDate())
                .setTime(resultSet.getTime("show_time").toLocalTime())
                .setTicketPrice(resultSet.getInt("ticket_price"))
                .build();
        movieSession.setId(resultSet.getLong("id"));
        return movieSession;
    }

}
