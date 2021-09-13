package com.my.cinema.booking.dao;

import com.my.cinema.booking.dao.interfaces.MovieDao;
import com.my.cinema.booking.dao.interfaces.ShoppingCartDao;
import com.my.cinema.booking.dao.interfaces.UserDao;

public abstract class DaoFactory {

    private static DaoFactory daoFactory;

    public abstract UserDao createUserDao();
    public abstract ShoppingCartDao createShoppingCartDao();
    public abstract MovieDao createMovieDao();

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