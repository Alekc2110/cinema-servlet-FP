package com.my.cinema.booking.model.entity;


public class Row extends Entity {

    private int number;

    public Row() {
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }


    @Override
    public String toString() {
        return "Row{" +
                "number=" + number +
                '}';
    }
}
