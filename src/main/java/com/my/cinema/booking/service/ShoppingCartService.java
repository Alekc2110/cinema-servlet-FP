package com.my.cinema.booking.service;

import com.my.cinema.booking.dao.DaoFactory;
import com.my.cinema.booking.dao.interfaces.ShoppingCartDao;
import org.apache.log4j.Logger;

public class ShoppingCartService {
    private static final Logger LOG = Logger.getLogger(ShoppingCartService.class);

    private DaoFactory factory = DaoFactory.getInstance();

    public boolean createShoppingCart(Long userId) {
        try (ShoppingCartDao dao = factory.createShoppingCartDao()) {
          return dao.saveShoppingCart(userId);
        }
    }

}
