package com.my.cinema.booking.model.entity;


import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Row)) return false;
        Row row = (Row) o;
        return  super.getId().equals(((Row) o).getId()) &&
                number == row.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.getId(), number);
    }
}
