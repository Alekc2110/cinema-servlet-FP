package com.my.cinema.booking.dao.impl;

import com.my.cinema.booking.dao.interfaces.OrderDao;
import com.my.cinema.booking.dao.mapper.Mapper;
import com.my.cinema.booking.dao.mapper.MovieMapper;
import com.my.cinema.booking.dao.mapper.MovieSessionMapper;
import com.my.cinema.booking.dao.mapper.SeatMapper;
import com.my.cinema.booking.model.entity.*;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.*;

import static com.my.cinema.booking.dao.constants.Queries.*;
import static com.my.cinema.booking.dao.constants.Queries.GET_MOVIE_BY_ID;


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
            Mapper<Seat> seatMapper = new SeatMapper();
            while (rs.next()) {
                LOG.debug("check if rs has next");
               Seat seat = seatMapper.getEntity(rs);
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
    public Optional<Seat> getSeatById(Long seatId) {
        Seat seat = null;
        try (PreparedStatement ps = connection.prepareStatement(GET_SEAT_BY_ID)) {
            ps.setLong(1, seatId);
            final ResultSet rs = ps.executeQuery();
            LOG.debug("Executed query: " + GET_SEAT_BY_ID);
            Mapper<Seat> seatMapper = new SeatMapper();
            while (rs.next()) {
                LOG.debug("check if rs has next");
                seat = seatMapper.getEntity(rs);
            }
            return Optional.ofNullable(seat);
        } catch (SQLException e) {
            LOG.error("SQLException in 'getSeatById' in JdbcOrderDao", e);
            return Optional.empty();
        }
    }

    @Override
    public Optional<Row> getRowById(Long rowId) {
        Row row = new Row();
        try (PreparedStatement ps = connection.prepareStatement(GET_ROW_BY_ID)) {
            ps.setLong(1, rowId);
            final ResultSet rs = ps.executeQuery();
            LOG.debug("Executed query: " + GET_ROW_BY_ID);
            while (rs.next()) {
                LOG.debug("check if rs has next");
                row.setNumber(rs.getInt("number"));
                row.setId(rs.getLong("id"));
            }
            return Optional.of(row);
        } catch (SQLException e) {
            LOG.error("SQLException in 'getRowById' in JdbcOrderDao", e);
            return Optional.empty();
        }
    }

    @Override
    public Optional<Long> saveOrder(Order order) {
        ResultSet generatedKey = null;
        try (PreparedStatement ps = connection.prepareStatement(SAVE_ORDER, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setTimestamp(1, Timestamp.valueOf(order.getOrderTime()), Calendar.getInstance(TimeZone.getDefault()));
            ps.setLong(2, order.getUserId());
            ps.setInt(3, order.getOrderPrice());
            ps.setString(4, String.valueOf(order.getOrderStatus()));
            if (ps.executeUpdate() != 1) {
                return Optional.empty();
            }
            generatedKey = ps.getGeneratedKeys();
            if (generatedKey.next()) {
                Long id = generatedKey.getLong(1);
                return Optional.of(id);
            }
        } catch (SQLException e) {
            LOG.error("SQLException in 'saveOrder' JDBCOrderDao: " + e);
            return Optional.empty();
        } finally {
            try {
                if (generatedKey != null) generatedKey.close();
            } catch (SQLException e) {
                LOG.error("SQLException when closing ResultSet in 'saveOrder': " + e);
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean updateOrder(Order order) {
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_ORDER_BY_ID)) {
            ps.setTimestamp(1, Timestamp.valueOf(order.getOrderTime()), Calendar.getInstance(TimeZone.getDefault()));
            ps.setLong(2, order.getUserId());
            ps.setInt(3, order.getOrderPrice());
            ps.setString(4, String.valueOf(order.getOrderStatus()));
            ps.setLong(5, order.getId());
            if (ps.executeUpdate() != 1) {
                return false;
            }
        } catch (SQLException e) {
            LOG.error("SQLException in updateOrder OrderDao: " + e);
            return false;
        }
        return true;
    }

    @Override
    public boolean saveSessionBookSeats(Long movieSesId, List<Ticket> list) {
        try (PreparedStatement ps = connection.prepareStatement(SAVE_BOOKED_SEATS)){
            connection.setAutoCommit(false);
            for (Ticket ticket : list) {
                ps.setLong(1, movieSesId);
                ps.setLong(2, ticket.getSeat().getId());
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
            LOG.error("SQLException in 'save booked seats' JDBCOrderDao: " + e);
            try {
                connection.rollback();
            } catch (SQLException ex) {
                LOG.error("Exception when trying to rollback:" + ex.getMessage());
            }
        } finally {
            try {
                connection.setAutoCommit(true);
                //connection.close();
            } catch (SQLException e) {
                LOG.error("SQLException when setAutoCommit true in 'saveSessionBookSeats': " + e);
            }
        }
        return false;
    }

    @Override
    public boolean deleteOrder(Long orderId) {
        try (PreparedStatement ps = connection.prepareStatement(DELETE_ORDER_BY_ID)) {
            ps.setLong(1, orderId);
            if (ps.executeUpdate() != 1) {
                return false;
            }
        } catch (SQLException e) {
            LOG.error("SQLException in deleteOrder OrderDao: " + e);
            return false;
        }
        return true;
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
