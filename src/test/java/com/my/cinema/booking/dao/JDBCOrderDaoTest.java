package com.my.cinema.booking.dao;

import com.my.cinema.booking.dao.impl.JDBCOrderDao;
import com.my.cinema.booking.dao.interfaces.OrderDao;
import com.my.cinema.booking.model.entity.Order;
import com.my.cinema.booking.model.entity.Row;
import com.my.cinema.booking.model.entity.Seat;
import com.my.cinema.booking.model.entity.Ticket;
import com.my.cinema.booking.model.enums.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.my.cinema.booking.dao.constants.Queries.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JDBCOrderDaoTest {
    @Mock
    private Connection connection;
    @Mock
    private PreparedStatement prepareStatement;
    @Mock
    private Statement statement;
    @Mock
    private ResultSet rs;

    private OrderDao orderDao;

    @BeforeEach
    void setUp() {
        orderDao = new JDBCOrderDao(connection);
    }

    @Test
    @DisplayName("should return list of booked seats")
    void findAllBookedSeatsTest() throws SQLException {
        when(connection.prepareStatement(eq(GET_BOOKED_SEATS))).thenReturn(prepareStatement);
        when(prepareStatement.executeQuery()).thenReturn(rs);

        List<Seat> resultList = orderDao.findAllBookedSeats(1L);

        Assertions.assertEquals(0, resultList.size());
    }

    @Test
    @DisplayName("should return empty list if sql exception happens")
    void findAllBookedSeatsExcTest() throws SQLException {
        when(connection.prepareStatement(eq(GET_BOOKED_SEATS))).thenReturn(prepareStatement);
        when(prepareStatement.executeQuery()).thenThrow(SQLException.class);

        List<Seat> resultList = orderDao.findAllBookedSeats(1L);

        Assertions.assertEquals(0, resultList.size());
    }

    @Test
    @DisplayName("should return list of seats")
    void getAllSeatsTest() throws SQLException {
        when(connection.createStatement()).thenReturn(statement);
        when(statement.executeQuery(GET_ALL_SEATS)).thenReturn(rs);

        List<Seat> allSeats = orderDao.getAllSeats();

        Assertions.assertTrue(allSeats.isEmpty());
    }

    @Test
    @DisplayName("should return empty list if sql exception happens")
    void getAllSeatsExcTest() throws SQLException {
        when(connection.createStatement()).thenReturn(statement);
        when(statement.executeQuery(GET_ALL_SEATS)).thenThrow(SQLException.class);

        List<Seat> allSeats = orderDao.getAllSeats();

        Assertions.assertTrue(allSeats.isEmpty());
    }

    @Test
    @DisplayName("should return seat by id")
    void getSeatByIdTest() throws SQLException {
        when(connection.prepareStatement(eq(GET_SEAT_BY_ID))).thenReturn(prepareStatement);
        when(prepareStatement.executeQuery()).thenReturn(rs);

        Optional<Seat> seatById = orderDao.getSeatById(1L);

        Assertions.assertEquals(Optional.empty(), seatById);
    }

    @Test
    @DisplayName("should return row by id")
    void getRowByIdTest() throws SQLException {
        when(connection.prepareStatement(eq(GET_ROW_BY_ID))).thenReturn(prepareStatement);
        when(prepareStatement.executeQuery()).thenReturn(rs);

        Optional<Row> rowById = orderDao.getRowById(1L);

        Assertions.assertEquals(Optional.empty(), rowById);
    }

    @Test
    @DisplayName("should save new order")
    void saveOrderTest() throws SQLException {
        Order order = new Order();
        order.setOrderStatus(Status.BOOKED);
        order.setOrderPrice(150);
        order.setUserId(1L);
        order.setOrderTime(LocalDateTime.now());

        when(connection.prepareStatement(eq(SAVE_ORDER), anyInt())).thenReturn(prepareStatement);

        when(prepareStatement.executeUpdate()).thenReturn(1);
        when(prepareStatement.getGeneratedKeys()).thenReturn(rs);

        Assertions.assertEquals(Optional.empty(), orderDao.saveOrder(order));
    }

    @Test
    @DisplayName("should return Optional empty if throws exception")
    void saveOrderExcTest() throws SQLException {
        Order order = new Order();
        order.setOrderStatus(Status.BOOKED);
        order.setOrderPrice(150);
        order.setUserId(1L);
        order.setOrderTime(LocalDateTime.now());

        when(connection.prepareStatement(eq(SAVE_ORDER), anyInt())).thenReturn(prepareStatement);

        when(prepareStatement.executeUpdate()).thenReturn(1);
        when(prepareStatement.getGeneratedKeys()).thenThrow(SQLException.class);

        Assertions.assertEquals(Optional.empty(), orderDao.saveOrder(order));
    }

    @Test
    @DisplayName("should update order by id")
    void updateOrderPositiveTest() throws SQLException {
        Order order = new Order();
        order.setOrderStatus(Status.BOOKED);
        order.setOrderPrice(150);
        order.setUserId(1L);
        order.setOrderTime(LocalDateTime.now());
        order.setId(1L);
        when(connection.prepareStatement(eq(UPDATE_ORDER_BY_ID))).thenReturn(prepareStatement);
        when(prepareStatement.executeUpdate()).thenReturn(1);

        Assertions.assertTrue(orderDao.updateOrder(order));
    }

    @Test
    @DisplayName("should return false if could not update order by id")
    void updateOrderNegativeTest() throws SQLException {
        Order order = new Order();
        order.setOrderStatus(Status.BOOKED);
        order.setOrderPrice(150);
        order.setUserId(1L);
        order.setOrderTime(LocalDateTime.now());
        order.setId(1L);
        when(connection.prepareStatement(eq(UPDATE_ORDER_BY_ID))).thenReturn(prepareStatement);
        when(prepareStatement.executeUpdate()).thenThrow(SQLException.class);

        Assertions.assertFalse(orderDao.updateOrder(order));
    }

    @Test
    @DisplayName("should return true when save booked seats by movie session")
    void saveSessionBookSeatsTest() throws SQLException {
        Seat seat1 = new Seat();
        seat1.setId(1L);
        seat1.setNumber(5);
        seat1.setRowId(5L);
        Seat seat2 = new Seat();
        seat2.setId(1L);
        seat2.setNumber(5);
        seat2.setRowId(5L);
        List<Ticket> tickets = Arrays.asList(new Ticket.Builder().setOrderId(1L).setSeat(seat1).build(),
                new Ticket.Builder().setOrderId(2L).setSeat(seat2).build());
        when(connection.prepareStatement(eq(SAVE_BOOKED_SEATS))).thenReturn(prepareStatement);

        when(prepareStatement.executeBatch()).thenReturn(new int[0]);

        Assertions.assertTrue(orderDao.saveSessionBookSeats(1L, tickets));
    }

    @Test
    @DisplayName("should return list booked seats by date")
    void getBookedSeatByDateTest() throws SQLException {
        when(connection.prepareStatement(eq(GET_BOOKED_SEATS_BY_DATE))).thenReturn(prepareStatement);
        when(prepareStatement.executeQuery()).thenReturn(rs);

        List<Seat> resultList = orderDao.getBookedSeatByDate(LocalDate.now());

        Assertions.assertEquals(0, resultList.size());

    }

    @Test
    @DisplayName("should delete order by id")
    void deleteOrderPositiveTest() throws SQLException {
        when(connection.prepareStatement(eq(DELETE_ORDER_BY_ID))).thenReturn(prepareStatement);
        when(prepareStatement.executeUpdate()).thenReturn(1);

        Assertions.assertTrue(orderDao.deleteOrder(1L));
    }

    @Test
    @DisplayName("should return false if could not delete order by id")
    void deleteMovieSessionByIdNegativeTest() throws SQLException {
        when(connection.prepareStatement(eq(DELETE_ORDER_BY_ID))).thenReturn(prepareStatement);
        when(prepareStatement.executeUpdate()).thenReturn(0);

        Assertions.assertFalse(orderDao.deleteOrder(1L));
    }


}
