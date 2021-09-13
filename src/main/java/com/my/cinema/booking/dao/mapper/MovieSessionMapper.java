package com.my.cinema.booking.dao.mapper;

import com.my.cinema.booking.model.entity.MovieSession;
import com.my.cinema.booking.model.entity.User;
import com.my.cinema.booking.model.enums.Role;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

public class MovieSessionMapper implements Mapper<MovieSession> {
    @Override
    public MovieSession getEntity(ResultSet resultSet) throws SQLException {
        MovieSession movieSession = new MovieSession.Builder()
                .setMovieId(Long.parseLong(resultSet.getString("movie_id")))
                .setShowTime(convertDate(resultSet.getTimestamp("show_time")))
                .setTicketPrice(Integer.parseInt(resultSet.getString("ticket_price")))
                .build();
        movieSession.setId(resultSet.getLong("id"));
        return movieSession;
    }

    private LocalDateTime convertDate(Timestamp timestamp) {
        return timestamp.toInstant().atZone(ZoneOffset.UTC).toLocalDateTime();
    }
}
