package com.my.cinema.booking.service;

import com.my.cinema.booking.dao.DaoFactory;
import com.my.cinema.booking.dao.interfaces.TicketDao;
import com.my.cinema.booking.exceptions.EntitySaveDaoException;
import com.my.cinema.booking.model.entity.Ticket;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class TicketService {
    private static final Logger LOG = Logger.getLogger(TicketService.class);

    private DaoFactory factory = DaoFactory.getInstance();

    public boolean saveTickets(List<Ticket> tickets) {
        try (TicketDao dao = factory.createTicketDao()) {
            LOG.debug("save new tickets in db: "+ tickets);
            return dao.saveTickets(tickets);
        }
    }
    public List<Ticket> findTicketsBySession(Long movieSesId) {
        try (TicketDao dao = factory.createTicketDao()) {
            LOG.debug("return list tickets by session id: "+ movieSesId);
            return dao.getTicketsBySession(movieSesId);
        }
    }

    public List<Ticket> findTicketByUserId(Long userId) {
        try (TicketDao dao = factory.createTicketDao()) {
            LOG.debug("return list tickets by user id: "+ userId);
            return dao.getTicketsByUserId(userId);
        }
    }

}
