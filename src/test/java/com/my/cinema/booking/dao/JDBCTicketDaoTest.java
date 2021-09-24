package com.my.cinema.booking.dao;


import com.my.cinema.booking.dao.impl.JDBCTicketDao;
import com.my.cinema.booking.dao.interfaces.TicketDao;
import com.my.cinema.booking.model.entity.MovieSession;
import com.my.cinema.booking.model.entity.Row;
import com.my.cinema.booking.model.entity.Seat;
import com.my.cinema.booking.model.entity.Ticket;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static com.my.cinema.booking.dao.constants.Queries.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JDBCTicketDaoTest {
    @Mock
    private Connection connection;
    @Mock
    private PreparedStatement prepareStatement;
    @Mock
    private ResultSet rs;

    private TicketDao ticketDao;

    @BeforeEach
    void setUp(){
        ticketDao = new JDBCTicketDao(connection);
    }

    @Test
    @DisplayName("should return true when save list tickets")
    void saveTicketsTest() throws SQLException {
        Seat seat1 = new Seat();
        seat1.setId(1L);
        seat1.setNumber(5);
        seat1.setRowId(5L);
        Seat seat2 = new Seat();
        seat2.setId(1L);
        seat2.setNumber(5);
        seat2.setRowId(5L);
        MovieSession ms1 = new MovieSession.Builder().
                setMovieId(1L).
                setDate(LocalDate.now()).
                setTime(LocalTime.NOON).
                setTicketPrice(150).
                build();
        ms1.setId(1L);
        MovieSession ms2 = new MovieSession.Builder().
                setMovieId(1L).
                setDate(LocalDate.now()).
                setTime(LocalTime.NOON).
                setTicketPrice(150).
                build();
        ms2.setId(1L);
        Row row = new Row(); row.setId(1L); row.setNumber(1);

        List<Ticket> tickets = Arrays.asList(new Ticket.Builder().setOrderId(1L).setSeat(seat1).setMovieSession(ms1).setRow(row).build(),
                new Ticket.Builder().setOrderId(2L).setSeat(seat2).setMovieSession(ms2).setRow(row).build());

        when(connection.prepareStatement(eq(SAVE_TICKET))).thenReturn(prepareStatement);
        when(prepareStatement.executeBatch()).thenReturn(new int[0]);

        Assertions.assertTrue(ticketDao.saveTickets(tickets));
    }


    @Test
    @DisplayName("should return false if sql exception happens")
    void saveTicketsExcTest() throws SQLException {
        Seat seat1 = new Seat();
        seat1.setId(1L);
        seat1.setNumber(5);
        seat1.setRowId(5L);
        Seat seat2 = new Seat();
        seat2.setId(1L);
        seat2.setNumber(5);
        seat2.setRowId(5L);
        MovieSession ms1 = new MovieSession.Builder().
                setMovieId(1L).
                setDate(LocalDate.now()).
                setTime(LocalTime.NOON).
                setTicketPrice(150).
                build();
        ms1.setId(1L);
        MovieSession ms2 = new MovieSession.Builder().
                setMovieId(1L).
                setDate(LocalDate.now()).
                setTime(LocalTime.NOON).
                setTicketPrice(150).
                build();
        ms2.setId(1L);
        Row row = new Row(); row.setId(1L); row.setNumber(1);

        List<Ticket> tickets = Arrays.asList(new Ticket.Builder().setOrderId(1L).setSeat(seat1).setMovieSession(ms1).setRow(row).build(),
                new Ticket.Builder().setOrderId(2L).setSeat(seat2).setMovieSession(ms2).setRow(row).build());

        when(connection.prepareStatement(eq(SAVE_TICKET))).thenReturn(prepareStatement);
        when(prepareStatement.executeBatch()).thenThrow(SQLException.class);

        Assertions.assertFalse(ticketDao.saveTickets(tickets));
    }

    @Test
    @DisplayName("should return list of tickets by movie session id")
    void getTicketsBySessionTest() throws SQLException {
        when(connection.prepareStatement(eq(GET_TICKETS_BY_SES))).thenReturn(prepareStatement);
        when(prepareStatement.executeQuery()).thenReturn(rs);

        List<Ticket> resultList = ticketDao.getTicketsBySession(1L);

        Assertions.assertEquals(0, resultList.size());
    }

    @Test
    @DisplayName("should return empty list if sql exception happens")
    void getTicketsBySessionExcTest() throws SQLException {
        when(connection.prepareStatement(eq(GET_TICKETS_BY_SES))).thenReturn(prepareStatement);
        when(prepareStatement.executeQuery()).thenThrow(SQLException.class);

        List<Ticket> resultList = ticketDao.getTicketsBySession(1L);

        Assertions.assertTrue(resultList.isEmpty());
    }


    @Test
    @DisplayName("should return list of tickets by user id")
    void getTicketsByUserIdTest() throws SQLException {
        when(connection.prepareStatement(eq(GET_TICKETS_BY_USER))).thenReturn(prepareStatement);
        when(prepareStatement.executeQuery()).thenReturn(rs);

        List<Ticket> resultList = ticketDao.getTicketsByUserId(1L);

        Assertions.assertEquals(0, resultList.size());
    }

    @Test
    @DisplayName("should return empty list if sql exception happens")
    void getTicketsByUserIdExcTest() throws SQLException {
        when(connection.prepareStatement(eq(GET_TICKETS_BY_USER))).thenReturn(prepareStatement);
        when(prepareStatement.executeQuery()).thenThrow(SQLException.class);

        List<Ticket> resultList = ticketDao.getTicketsByUserId(1L);

        Assertions.assertTrue(resultList.isEmpty());
    }




}
