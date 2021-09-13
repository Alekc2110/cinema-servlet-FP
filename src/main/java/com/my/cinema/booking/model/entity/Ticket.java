package com.my.cinema.booking.model.entity;

public class Ticket extends Entity {

    private User user;
    private MovieSession movieSession;
    private Row row;
    private Seat seat;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

        private User user;
        private MovieSession movieSession;
        private Row row;
        private Seat seat;


        public Builder setUser(User user) {
            this.user = user;
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
            ticket.user = this.user;
            ticket.movieSession = this.movieSession;
            ticket.row = this.row;
            ticket.seat = this.seat;

            return ticket;
        }
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "user=" + user +
                ", movieSession=" + movieSession +
                ", row=" + row +
                ", seat=" + seat +
                '}';
    }
}
