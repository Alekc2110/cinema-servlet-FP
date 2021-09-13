package com.my.cinema.booking.model.entity;

public class Seat extends Entity {

    private int number;

    public Seat(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "number=" + number +
                '}';
    }
}
