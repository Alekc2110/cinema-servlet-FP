package com.my.cinema.booking.service;

import com.my.cinema.booking.dao.DaoFactory;
import com.my.cinema.booking.dao.interfaces.OrderDao;
import com.my.cinema.booking.model.entity.Order;
import com.my.cinema.booking.model.entity.Row;
import com.my.cinema.booking.model.entity.Seat;
import com.my.cinema.booking.model.entity.Ticket;
import com.my.cinema.booking.model.enums.Status;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderDao orderDao;

    @Mock
    private DaoFactory factory;

    private OrderService orderService;

    @BeforeEach
    void setUp() {
        setMock(factory);
        orderService = new OrderService();
        when(factory.createOrderDao()).thenReturn(orderDao);
    }

    @AfterEach
    public void resetSingleton() throws Exception {
        Field instance = DaoFactory.class.getDeclaredField("daoFactory");
        instance.setAccessible(true);
        instance.set(null, null);
    }

    @Test
    @DisplayName("should return list of booked seats")
    void findAllBookedSeatsTest() {
        Long movieSessionId = 5L;
        Seat one = new Seat(1L, 1);
        Seat two = new Seat(2L, 2);
        List<Seat> bookedSeats = Arrays.asList(one, two);
        when(orderDao.findAllBookedSeats(movieSessionId)).thenReturn(bookedSeats);

        List<Seat> allBookedSeats = orderService.findAllBookedSeats(5L);

        Assertions.assertAll(() -> {
            assertEquals(2L, allBookedSeats.size());
            assertEquals(one, allBookedSeats.get(0));
            assertTrue(allBookedSeats.contains(two));
        });
    }

    @Test
    @DisplayName("should return list of all seats")
    void getAllSeatsTest() {
        Seat one = new Seat(1L, 1);
        Seat two = new Seat(2L, 2);
        List<Seat> allSeats = Arrays.asList(one, two);
        when(orderDao.getAllSeats()).thenReturn(allSeats);

        List<Seat> result = orderService.getAllSeats();

        Assertions.assertAll(() -> {
            assertEquals(2L, result.size());
            assertEquals(one, result.get(0));
            assertTrue(result.contains(two));
        });
    }

    @Test
    @DisplayName("should return seat by seatId")
    void getSeatByIdTest() {
        Long seatId = 1L;
        Seat one = new Seat(1L, 1);
        one.setId(seatId);
        when(orderDao.getSeatById(seatId)).thenReturn(Optional.of(one));

        Seat result = orderService.getSeatById(seatId);

        Assertions.assertEquals(1L, result.getId());
    }

    @Test
    @DisplayName("should return row by rowId")
    void getRowByIdTest() {
        Long rowId = 111L;
        Row row = new Row();
        row.setId(rowId);
        row.setNumber(1);

        when(orderDao.getRowById(rowId)).thenReturn(Optional.of(row));

        Row result = orderService.getRowById(rowId);

        Assertions.assertEquals(111L, result.getId());
    }

    @Test
    @DisplayName("should save order")
    void saveOrderTest() {
        Long newId = 25L;
        Order order = new Order(LocalDateTime.now(), 55L, 250, Status.BOOKED);
        order.setId(newId);
        when(orderDao.saveOrder(order)).thenReturn(Optional.of(newId));

        Long resultId = orderService.saveOrder(order);

        Assertions.assertEquals(newId, resultId);
    }

    @Test
    @DisplayName("should update order")
    void updateOrderTest() {
        Long newId = 20L;
        Order update = new Order(LocalDateTime.now(), 55L, 250, Status.BOOKED);
        update.setId(newId);
        when(orderDao.updateOrder(update)).thenReturn(true);

        boolean result = orderService.updateOrder(update);

        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("should save booked seats from tickets in db")
    void saveSessionBookSeatsTest() {
        Long movieSesId = 10L;
        List<Ticket> tickets = Arrays.asList(new Ticket.Builder().setOrderId(20L).build(),
                                             new Ticket.Builder().setOrderId(21L).build());
        when(orderDao.saveSessionBookSeats(movieSesId, tickets)).thenReturn(true);

        boolean result = orderService.saveSessionBookSeats(movieSesId, tickets);

        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("should delete order by id")
    void deleteOrderTest() {
        Long orderId = 10L;
        when(orderDao.deleteOrder(orderId)).thenReturn(true);

        boolean result = orderService.deleteOrder(10L);

        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("should return count of booked seats by date")
    void getCountBookedSeatByDateTest() {
        Seat one = new Seat(1L, 1);
        Seat two = new Seat(2L, 2);
        List<Seat> allSeats = Arrays.asList(one, two);


        when(orderDao.getBookedSeatByDate(LocalDate.now())).thenReturn(allSeats);

        int result = orderService.getCountBookedSeatByDate(LocalDate.now());

        Assertions.assertEquals(2, result);
    }


    private void setMock(DaoFactory mock) {
        Field instance;
        try {
            instance = DaoFactory.class.getDeclaredField("daoFactory");
            instance.setAccessible(true);
            instance.set(instance, mock);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
