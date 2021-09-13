package com.my.cinema.booking.dao.impl;

import com.my.cinema.booking.dao.interfaces.ShoppingCartDao;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.my.cinema.booking.dao.constants.Queries.SAVE_SHOPPING_CART;


public class JDBCShoppingCartDao implements ShoppingCartDao {
    private static final Logger LOG = Logger.getLogger(JDBCShoppingCartDao.class);
    private Connection connection;

    public JDBCShoppingCartDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean saveShoppingCart(Long userId) {
         try (PreparedStatement ps = connection.prepareStatement(SAVE_SHOPPING_CART)) {
            ps.setLong(1, userId);
             if (ps.executeUpdate() != 1) {
                 return false;
             }
         } catch (SQLException e) {
             LOG.info("SQLException in saveShoppingCart shoppingCartDao: " + e);
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
