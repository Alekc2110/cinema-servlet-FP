package com.my.cinema.booking.model.entity;

import java.util.List;

public class ShoppingCart extends Entity {

    private List<Order> orders;

    public ShoppingCart() {
    }

    public ShoppingCart(List<Order> orders) {
        this.orders = orders;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "orders=" + orders +
                '}';
    }
}
