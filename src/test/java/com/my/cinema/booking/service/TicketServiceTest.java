package com.my.cinema.booking.service;

import com.my.cinema.booking.dao.DaoFactory;
import com.my.cinema.booking.dao.interfaces.OrderDao;
import com.my.cinema.booking.dao.interfaces.TicketDao;
import com.my.cinema.booking.model.entity.Order;
import com.my.cinema.booking.model.entity.Ticket;
import com.my.cinema.booking.model.enums.Status;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TicketServiceTest {

    @Mock
    private TicketDao ticketDao;

    @Mock
    private DaoFactory factory;

    private TicketService ticketService;

    @BeforeEach
    void setUp() {
        setMock(factory);
        ticketService = new TicketService();
        when(factory.createTicketDao()).thenReturn(ticketDao);
    }

    @AfterEach
    public void resetSingleton() throws Exception {
        Field instance = DaoFactory.class.getDeclaredField("daoFactory");
        instance.setAccessible(true);
        instance.set(null, null);
    }


    @Test
    @DisplayName("should tickets list")
    void saveTicketsTest() {
        List<Ticket> tickets = Arrays.asList(new Ticket.Builder().setOrderId(20L).build(),
                new Ticket.Builder().setOrderId(21L).build());

        when(ticketDao.saveTickets(tickets)).thenReturn(true);

        boolean result = ticketService.saveTickets(tickets);

        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("should return tickets list by session id")
    void findTicketsBySessionTest() {
        Long sessionId = 25L;
        List<Ticket> tickets = Arrays.asList(new Ticket.Builder().setOrderId(20L).build(),
                new Ticket.Builder().setOrderId(21L).build());

        when(ticketDao.getTicketsBySession(sessionId)).thenReturn(tickets);

        List<Ticket> result = ticketService.findTicketsBySession(25L);

        Assertions.assertAll(() -> {
            assertEquals(2L, result.size());
            assertEquals(20L, result.get(0).getOrderId());
        });
    }

    @Test
    @DisplayName("should return tickets list by user id")
    void findTicketByUserIdTest() {
        Long userId = 2L;
        List<Ticket> tickets = Arrays.asList(new Ticket.Builder().setOrderId(20L).build(),
                new Ticket.Builder().setOrderId(21L).build());

        when(ticketDao.getTicketsByUserId(userId)).thenReturn(tickets);

        List<Ticket> result = ticketService.findTicketByUserId(2L);

        Assertions.assertAll(() -> {
            assertEquals(2L, result.size());
            assertEquals(20L, result.get(0).getOrderId());
            assertEquals(21L, result.get(1).getOrderId());
        });
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
