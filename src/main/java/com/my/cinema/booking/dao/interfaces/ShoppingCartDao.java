package com.my.cinema.booking.dao.interfaces;

import com.my.cinema.booking.model.entity.User;

import java.util.Optional;

public interface ShoppingCartDao extends AutoCloseable {

    boolean saveShoppingCart(Long userId);

    void close();
}