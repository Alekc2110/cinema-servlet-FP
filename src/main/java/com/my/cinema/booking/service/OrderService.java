package com.my.cinema.booking.service;

import com.my.cinema.booking.dao.DaoFactory;
import com.my.cinema.booking.dao.interfaces.OrderDao;
import com.my.cinema.booking.exceptions.EntityNotFoundException;
import com.my.cinema.booking.exceptions.EntitySaveDaoException;
import com.my.cinema.booking.model.entity.Order;
import com.my.cinema.booking.model.entity.Row;
import com.my.cinema.booking.model.entity.Seat;
import com.my.cinema.booking.model.entity.Ticket;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class OrderService {
    private static final Logger LOG = Logger.getLogger(OrderService.class);

    private DaoFactory factory = DaoFactory.getInstance();

    public List<Order> findAllOrders() {
        try (OrderDao dao = factory.createOrderDao()) {
            LOG.debug("find all orders");
            return dao.findAllOrders();
        }
    }

    public List<Seat> findAllBookedSeats(Long movieSessionId) {
        try (OrderDao dao = factory.createOrderDao()) {
            LOG.debug("find all booked seats by movieSessionId: " + movieSessionId);
            return dao.findAllBookedSeats(movieSessionId);
        }
    }

    public List<Seat> getAllSeats() {
        try (OrderDao dao = factory.createOrderDao()) {
            LOG.debug("return all seats");
            return dao.getAllSeats();
        }
    }

    public Seat getSeatById(Long seatId) {
        try (OrderDao dao = factory.createOrderDao()) {
            LOG.debug("return seat by seatId: " + seatId);
            Optional<Seat> seatById = dao.getSeatById(seatId);
            return seatById.orElseThrow(EntityNotFoundException::new);
        }
    }

    public Row getRowById(Long rowId) {
        try (OrderDao dao = factory.createOrderDao()) {
            LOG.debug("return row by rowId: " + rowId);
            Optional<Row> rowByIdOpt = dao.getRowById(rowId);
            return rowByIdOpt.orElseThrow(EntityNotFoundException::new);
        }
    }

    public Long saveOrder(Order order) {
        try (OrderDao dao = factory.createOrderDao()) {
            LOG.debug("save new order with status BOOKED: " + order);
            Optional<Long> orderIdOpt = dao.saveOrder(order);
            return orderIdOpt.orElseThrow(EntitySaveDaoException::new);
        }
    }

    public boolean updateOrder(Order order) {
        try (OrderDao dao = factory.createOrderDao()) {
            LOG.debug("update order status for order: " + order);
            return dao.updateOrder(order);
        }
    }

    public boolean saveSessionBookSeats(Long movieSesId, List<Ticket> tickets) {
        try (OrderDao dao = factory.createOrderDao()) {
            LOG.debug("update seats, add booked seats to DB");
            return dao.saveSessionBookSeats(movieSesId, tickets);
        }
    }

    public boolean deleteOrder(Long orderId) {
        try (OrderDao dao = factory.createOrderDao()) {
            LOG.debug("delete order by orderId: " + orderId);
            return dao.deleteOrder(orderId);
        }
    }

    public int getCountBookedSeatByDate(LocalDate date) {
        AtomicInteger count = new AtomicInteger();
        try (OrderDao dao = factory.createOrderDao()) {
            LOG.debug("return count of booked seats by date: " + date);
            final List<Seat> bookedSeatByDate = dao.getBookedSeatByDate(date);
            bookedSeatByDate.forEach(seat -> count.getAndIncrement());
            return count.get();
        }
    }

}
