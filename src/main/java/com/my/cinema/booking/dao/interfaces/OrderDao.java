package com.my.cinema.booking.dao.interfaces;

import com.my.cinema.booking.model.entity.Order;
import com.my.cinema.booking.model.entity.Row;
import com.my.cinema.booking.model.entity.Seat;
import com.my.cinema.booking.model.entity.Ticket;

import java.util.List;
import java.util.Optional;

public interface OrderDao extends AutoCloseable {

    List<Order> findAllOrders();

    List<Seat> findAllBookedSeats(Long movieSesId);

    List<Seat> getAllSeats();

    Optional<Seat> getSeatById(Long seatId);

    Optional<Row> getRowById(Long rowId);

    Optional<Long> saveOrder(Order order);

    boolean updateOrder(Order order);

    boolean saveSessionBookSeats(Long movieSesId, List<Ticket> list);

    boolean deleteOrder(Long orderId);

    void close();

}
