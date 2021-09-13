package com.my.cinema.booking.dao.interfaces;

public interface ShoppingCartDao extends AutoCloseable {

    boolean saveShoppingCart(Long userId);

    void close();
}