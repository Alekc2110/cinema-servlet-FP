package com.my.cinema.booking.dao;

import com.my.cinema.booking.dao.impl.JDBCMovieDao;
import com.my.cinema.booking.dao.impl.JDBCOrderDao;
import com.my.cinema.booking.dao.impl.JDBCTicketDao;
import com.my.cinema.booking.dao.impl.JDBCUserDao;
import com.my.cinema.booking.dao.interfaces.*;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {
    private static final Logger LOG = Logger.getLogger(JDBCDaoFactory.class);
    private DataSource dataSource = ConnectionPoolHolder.getDataSource();

    private Connection getConnection() {
        try {
            LOG.debug("getConnection: " + dataSource);
            return dataSource.getConnection();
        } catch (SQLException e) {
            LOG.debug("SQLException occurred", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserDao createUserDao() {
        return new JDBCUserDao(getConnection());
    }

    @Override
    public MovieDao createMovieDao() {
        return new JDBCMovieDao(getConnection());
    }

    @Override
    public OrderDao createOrderDao() {
        return new JDBCOrderDao(getConnection());
    }

    @Override
    public TicketDao createTicketDao() {
        return new JDBCTicketDao(getConnection());
    }
}
