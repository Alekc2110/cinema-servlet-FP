package com.my.cinema.booking.model.entity;

public class Seat extends Entity {

    private int rowId;
    private int number;

    public Seat() {
    }

    public Seat(int rowId, int number) {
        this.rowId = rowId;
        this.number = number;
    }

    public int getRowId() {
        return rowId;
    }

    public void setRowId(int rowId) {
        this.rowId = rowId;
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
                "rowId=" + rowId +
                ", number=" + number +
                '}';
    }
}
