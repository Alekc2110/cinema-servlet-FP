package com.my.cinema.booking.model.entity;

import java.time.LocalDateTime;
import java.util.List;


public class Order extends Entity {

    private List<Ticket> ticketList;
    private LocalDateTime orderTime;

    public Order(List<Ticket> ticketList, LocalDateTime orderTime) {
        this.ticketList = ticketList;
        this.orderTime = orderTime;
    }

    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

    @Override
    public String toString() {
        return "Order{" +
                "ticketList=" + ticketList +
                ", orderTime=" + orderTime +
                '}';
    }
}
