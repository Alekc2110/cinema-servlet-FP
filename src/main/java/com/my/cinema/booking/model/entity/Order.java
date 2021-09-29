package com.my.cinema.booking.model.entity;

import com.my.cinema.booking.model.enums.Status;

import java.time.LocalDateTime;
import java.util.List;


public class Order extends Entity {
    private LocalDateTime orderTime;
    private Long userId;
    private int orderPrice;
    private Status orderStatus;
    private List<Ticket> ticketList;

    public Order() {

    }

    public Order(LocalDateTime orderTime, Long userId, int orderPrice, Status orderStatus) {
        this.orderTime = orderTime;
        this.userId = userId;
        this.orderPrice = orderPrice;
        this.orderStatus = orderStatus;
    }

    public Status getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Status orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(int orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
                "orderTime=" + orderTime +
                ", userId=" + userId +
                ", orderPrice=" + orderPrice +
                ", orderStatus=" + orderStatus +
                ", ticketList=" + ticketList +
                '}';
    }
}
