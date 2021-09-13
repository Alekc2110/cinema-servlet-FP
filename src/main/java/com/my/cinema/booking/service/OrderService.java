package com.my.cinema.booking.service;

import com.my.cinema.booking.dao.DaoFactory;
import com.my.cinema.booking.dao.interfaces.OrderDao;
import com.my.cinema.booking.model.entity.Order;
import com.my.cinema.booking.model.entity.Seat;
import org.apache.log4j.Logger;

import java.util.List;

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

}
