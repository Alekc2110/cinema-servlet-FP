package com.my.cinema.booking.model.entity;


import java.util.List;

public class Row extends Entity {

    private List<Seat> seats;

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public Row(List<Seat> seats) {
        this.seats = seats;
    }

    @Override
    public String toString() {
        return "Row{" +
                "seats=" + seats +
                '}';
    }
}
