package com.my.cinema.booking.dao.interfaces;

import com.my.cinema.booking.model.entity.Order;
import com.my.cinema.booking.model.entity.Seat;

import java.util.List;

public interface OrderDao extends AutoCloseable {

    List<Order> findAllOrders();

    List<Seat> findAllBookedSeats(Long movieSesId);

    List<Seat> getAllSeats();

    void close();

}
