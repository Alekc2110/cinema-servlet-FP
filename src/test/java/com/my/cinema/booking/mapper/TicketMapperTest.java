package com.my.cinema.booking.mapper;

import com.my.cinema.booking.dao.mapper.SeatMapper;
import com.my.cinema.booking.dao.mapper.TicketMapper;
import com.my.cinema.booking.model.entity.MovieSession;
import com.my.cinema.booking.model.entity.Row;
import com.my.cinema.booking.model.entity.Seat;
import com.my.cinema.booking.model.entity.Ticket;
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
public class TicketMapperTest {

    @Test
    void getEntityTest() throws SQLException {
        TicketMapper ticketMapper = new TicketMapper();
        ResultSet rs = mock(ResultSet.class);
        Row row = new Row(); row.setId(5L); row.setNumber(2);
        MovieSession movieSession = new MovieSession.Builder().
                setTime(LocalTime.NOON).setMovieId(1L).setTicketPrice(150).setDate(LocalDate.now()).build();
        movieSession.setId(1L);
        Seat seat = new Seat(); seat.setId(1L); seat.setNumber(5); seat.setRowId(5L);

        Ticket expected = new Ticket.Builder().
                setOrderId(1L).
                setRow(row).
                setMovieSession(movieSession).
                setSeat(seat).
                build();

        lenient().when(rs.getLong("order_id")).thenReturn(1L);
        lenient().when(rs.getLong("r.id")).thenReturn(5L);
        lenient().when(rs.getInt("r.number")).thenReturn(2);
        lenient().when(rs.getLong("movie_id")).thenReturn(1L);
        lenient().when(rs.getDate("show_date")).thenReturn(Date.valueOf(LocalDate.now()));
        lenient().when(rs.getTime("show_time")).thenReturn(Time.valueOf(LocalTime.NOON));
        lenient().when(rs.getInt("ticket_price")).thenReturn(150);
        lenient().when(rs.getLong("ms.id")).thenReturn(1L);
        lenient().when(rs.getLong("s.row_id")).thenReturn(5L);
        lenient().when(rs.getInt("s.number")).thenReturn(5);
        lenient().when(rs.getLong("s.id")).thenReturn(1L);

        Ticket result = ticketMapper.getEntity(rs);

        Assertions.assertAll(() -> {
            assertEquals(expected.getId(), result.getId());
            assertEquals(expected.getRow(), result.getRow());
            assertEquals(expected.getSeat(), result.getSeat());
            assertEquals(expected.getMovieSession(), result.getMovieSession());
        });
    }

}
