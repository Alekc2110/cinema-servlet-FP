package com.my.cinema.booking.model.entity;

import java.util.Objects;

public class Ticket extends Entity {
    private Long orderId;
    private MovieSession movieSession;
    private Row row;
    private Seat seat;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public MovieSession getMovieSession() {
        return movieSession;
    }

    public void setMovieSession(MovieSession movieSession) {
        this.movieSession = movieSession;
    }

    public Row getRow() {
        return row;
    }

    public void setRow(Row row) {
        this.row = row;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public static class Builder {
        private Long orderId;
        private MovieSession movieSession;
        private Row row;
        private Seat seat;


        public Builder setOrderId(Long orderId) {
            this.orderId = orderId;
            return this;
        }
        public Builder setMovieSession(MovieSession movieSession) {
            this.movieSession = movieSession;
            return this;
        }
        public Builder setRow(Row row) {
            this.row = row;
            return this;
        }
        public Builder setSeat(Seat seat) {
            this.seat = seat;
            return this;
        }

        public Ticket build() {
            Ticket ticket = new Ticket();
            ticket.orderId = this.orderId;
            ticket.movieSession = this.movieSession;
            ticket.row = this.row;
            ticket.seat = this.seat;

            return ticket;
        }
    }

    @Override
    public String toString() {
        return "Ticket{" + "ID: "+ super.getId() +
                ",orderId=" + orderId +
                ", movieSession=" + movieSession +
                ", row=" + row +
                ", seat=" + seat +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return  Objects.equals(movieSession, ticket.movieSession) &&
                Objects.equals(row, ticket.row) &&
                Objects.equals(seat, ticket.seat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieSession, row, seat);
    }
}
