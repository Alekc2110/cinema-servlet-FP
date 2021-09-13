package com.my.cinema.booking.dao.mapper;

import com.my.cinema.booking.model.entity.Movie;
import com.my.cinema.booking.model.entity.User;
import com.my.cinema.booking.model.enums.Role;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieMapper implements Mapper<Movie> {
    @Override
    public Movie getEntity(ResultSet resultSet) throws SQLException {
        Movie movie = new Movie.Builder()
                .setTitle(resultSet.getString("title"))
                .setDescription(resultSet.getString("description"))
                .setPhotoUrl(resultSet.getString("photo_url"))
                .setDirector(resultSet.getString("director"))
                .setCountry(resultSet.getString("country"))
                .setYear(resultSet.getString("year"))
                .build();
        movie.setId(resultSet.getLong("id"));
        return movie;
    }
}
