package com.my.cinema.booking.mapper;

import com.my.cinema.booking.dao.mapper.MovieMapper;
import com.my.cinema.booking.dao.mapper.MovieSessionMapper;
import com.my.cinema.booking.model.entity.Movie;
import com.my.cinema.booking.model.entity.MovieSession;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class MovieSessionMapperTest {

    @Test
    void getEntityTest() throws SQLException {
        MovieSessionMapper movieSessionMapper = new MovieSessionMapper();
        ResultSet rs = mock(ResultSet.class);
        MovieSession expected = new MovieSession.Builder().
                setMovieId(1L).
                setDate(LocalDate.now()).
                setTime(LocalTime.NOON).
                setTicketPrice(150).
                build();
        expected.setId(1L);

        lenient().when(rs.getLong("movie_id")).thenReturn(1L);
        lenient().when(rs.getDate("show_date")).thenReturn(Date.valueOf((LocalDate.now())));
        lenient().when(rs.getTime("show_time")).thenReturn(Time.valueOf((LocalTime.NOON)));
        lenient().when(rs.getInt("ticket_price")).thenReturn(150);
        lenient().when(rs.getLong("id")).thenReturn(1L);

        MovieSession result = movieSessionMapper.getEntity(rs);


        Assertions.assertAll(()-> {
            assertEquals(expected.getId(), result.getId());
            assertEquals(expected.getMovieId(), result.getMovieId());
            assertEquals(expected.getDate(), result.getDate());
            assertEquals(expected.getTime(), result.getTime());
            assertEquals(expected.getTicketPrice(), result.getTicketPrice());

        });
    }
}
