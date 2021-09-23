package com.my.cinema.booking.dao.interfaces;

import com.my.cinema.booking.model.entity.Ticket;

import java.util.List;
import java.util.Optional;

public interface TicketDao extends AutoCloseable{

    boolean saveTickets(List<Ticket> tickets);

    List<Ticket> getTicketsBySession(Long movieSesId);

    List<Ticket> getTicketsByUserId(Long userId);

    void close();
}
