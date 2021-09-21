package com.my.cinema.booking.dao;

import com.my.cinema.booking.dao.interfaces.*;

public abstract class DaoFactory {

    private static DaoFactory daoFactory;

    public abstract UserDao createUserDao();
    public abstract MovieDao createMovieDao();
    public abstract OrderDao createOrderDao();
    public abstract TicketDao createTicketDao();

    public static DaoFactory getInstance() {
        if (daoFactory == null) {
            synchronized (DaoFactory.class) {
                if (daoFactory == null) {
                    daoFactory = new JDBCDaoFactory();
                }
            }
        }
        return daoFactory;
    }
}