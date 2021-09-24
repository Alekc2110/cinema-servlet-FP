package com.my.cinema.booking.mapper;

import com.my.cinema.booking.dao.mapper.MovieMapper;
import com.my.cinema.booking.model.entity.Movie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class MovieMapperTest {

    @Test
    void getEntityTest() throws SQLException {
        MovieMapper movieMapper = new MovieMapper();
        ResultSet rs = mock(ResultSet.class);
        Movie expected = new Movie.Builder().
                setTitle("title").
                setDescription("description").
                setCountry("USA").
                setYear("2021").
                setDirector("director").
                setPhotoUrl("url").
                build();
        expected.setId(1L);

        lenient().when(rs.getLong("id")).thenReturn(1L);
        lenient().when(rs.getString("title")).thenReturn("title");
        lenient().when(rs.getString("description")).thenReturn("description");
        lenient().when(rs.getString("photo_url")).thenReturn("url");
        lenient().when(rs.getString("director")).thenReturn("director");
        lenient().when(rs.getString("year")).thenReturn("2021");


        Movie result = movieMapper.getEntity(rs);

        Assertions.assertAll(()-> {
            assertEquals(expected.getTitle(), result.getTitle());
            assertEquals(expected.getDescription(), result.getDescription());
            assertEquals(expected.getPhotoUrl(), result.getPhotoUrl());
            assertEquals(expected.getDirector(), result.getDirector());
            assertEquals(expected.getYear(), result.getYear());
            assertEquals(expected.getId(), result.getId());
        });
    }
}
