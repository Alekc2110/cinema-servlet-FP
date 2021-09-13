package com.my.cinema.booking.dao.impl;

import com.my.cinema.booking.dao.interfaces.OrderDao;
import com.my.cinema.booking.dao.mapper.Mapper;
import com.my.cinema.booking.dao.mapper.MovieSessionMapper;
import com.my.cinema.booking.dao.mapper.SeatMapper;
import com.my.cinema.booking.model.entity.MovieSession;
import com.my.cinema.booking.model.entity.Order;
import com.my.cinema.booking.model.entity.Seat;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.my.cinema.booking.dao.constants.Queries.GET_ALL_SEATS;
import static com.my.cinema.booking.dao.constants.Queries.GET_BOOKED_SEATS;


public class JDBCOrderDao implements OrderDao {
    private static final Logger LOG = Logger.getLogger(JDBCOrderDao.class);
    private Connection connection;

    public JDBCOrderDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Order> findAllOrders() {
        return null;
    }

    @Override
    public List<Seat> findAllBookedSeats(Long movieSesId) {
        List<Seat> bookedSeatsList = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(GET_BOOKED_SEATS)) {
            ps.setLong(1, movieSesId);
            final ResultSet rs = ps.executeQuery();
            LOG.debug("Executed query: " + GET_BOOKED_SEATS);
            Mapper<Seat> SeatMapper = new SeatMapper();
            while (rs.next()) {
                LOG.debug("check if rs has next");
               Seat seat = SeatMapper.getEntity(rs);
                bookedSeatsList.add(seat);
            }
            return bookedSeatsList;
        } catch (SQLException e) {
            LOG.error("SQLException in 'findAllBookedSeats' in JdbcOrderDao", e);
            return bookedSeatsList;
        }
    }

    @Override
    public List<Seat> getAllSeats() {
        List<Seat> seatsList = new ArrayList<>();
        try (Statement st = connection.createStatement()) {
            final ResultSet rs = st.executeQuery(GET_ALL_SEATS);
            LOG.debug("Executed query: " + GET_ALL_SEATS);
            Mapper<Seat> SeatMapper = new SeatMapper();
            while (rs.next()) {
                LOG.debug("check if rs has next");
                Seat seat = SeatMapper.getEntity(rs);
                seatsList.add(seat);
            }
            return seatsList;
        } catch (SQLException e) {
            LOG.error("SQLException in 'findAllSeats' in JdbcOrderDao", e);
            return seatsList;
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
