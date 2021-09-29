package com.my.cinema.booking.model.entity;

import com.my.cinema.booking.model.enums.Status;

import java.util.Objects;

public class Seat extends Entity {
    private Long rowId;
    private int number;
    private Status status;

    public Seat() {
    }

    public Seat(Long rowId, int number) {
        this.rowId = rowId;
        this.number = number;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getRowId() {
        return rowId;
    }

    public void setRowId(Long rowId) {
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
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seat seat = (Seat) o;
        return super.getId().equals(((Seat) o).getId()) && rowId.equals(seat.rowId) &&
                number == seat.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.getId(), rowId, number);
    }
}
