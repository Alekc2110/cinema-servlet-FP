package com.my.cinema.booking.dao.impl;

import com.my.cinema.booking.dao.interfaces.TicketDao;
import com.my.cinema.booking.dao.mapper.Mapper;
import com.my.cinema.booking.dao.mapper.SeatMapper;
import com.my.cinema.booking.dao.mapper.TicketMapper;
import com.my.cinema.booking.model.entity.Seat;
import com.my.cinema.booking.model.entity.Ticket;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import static com.my.cinema.booking.dao.constants.Queries.*;

public class JDBCTicketDao implements TicketDao {
    private static final Logger LOG = Logger.getLogger(JDBCTicketDao.class);
    private Connection connection;


    public JDBCTicketDao(Connection connection) {
        this.connection = connection;
    }


    @Override
    public boolean saveTickets(List<Ticket> tickets) {
        try(PreparedStatement ps = connection.prepareStatement(SAVE_TICKET)) {
            connection.setAutoCommit(false);
            for (Ticket ticket : tickets) {
                ps.setLong(1, ticket.getMovieSession().getId());
                ps.setLong(2, ticket.getRow().getId());
                ps.setLong(3, ticket.getSeat().getId());
                ps.setLong(4, ticket.getOrderId());
                ps.addBatch();
            }
            int[] results = ps.executeBatch();
            for (int i : results) {
                if (i < 0) {
                    return false;
                }
            }
            connection.commit();
            return true;
        } catch (SQLException e) {
            LOG.error("SQLException in 'saveTickets' JDBCTicketDao: " + e);
            try {
                connection.rollback();
            } catch (SQLException ex) {
                LOG.error("Exception when trying to rollback:" + ex.getMessage());
            }
        } finally {
            try {
                connection.setAutoCommit(true);
//                connection.close();
            } catch (SQLException e) {
                LOG.error("SQLException when setAutoCommit true in 'saveTickets': " + e);
            }
        }
        return false;
    }

    @Override
    public List<Ticket> getTicketsBySession(Long movieSesId) {
        List<Ticket> ticketList = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(GET_TICKETS)) {
            ps.setLong(1, movieSesId);
            final ResultSet rs = ps.executeQuery();
            LOG.debug("Executed query: " + GET_TICKETS);
            Mapper<Ticket> ticketMapper = new TicketMapper();
            while (rs.next()) {
                LOG.debug("check if rs has next");
                Ticket ticket = ticketMapper.getEntity(rs);
                ticketList.add(ticket);
            }
            return ticketList;
        } catch (SQLException e) {
            LOG.error("SQLException in 'getTicketsBySession' in JdbcTicketDao", e);
            return ticketList;
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
