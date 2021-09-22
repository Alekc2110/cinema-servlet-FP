package com.my.cinema.booking.model.entity;

import java.time.LocalDate;
import java.time.LocalTime;


public class MovieSession extends Entity {

    private Long movieId;
    private LocalDate date;
    private LocalTime time;
    private int ticketPrice;

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(int ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public static class Builder {

        private Long movieId;
        private LocalDate date;
        private LocalTime time;
        private int ticketPrice;

        public Builder setMovieId(Long movieId) {
            this.movieId = movieId;

            return this;
        }
        public Builder setDate(LocalDate date) {
            this.date = date;

            return this;
        }

        public Builder setTime(LocalTime time) {
            this.time = time;

            return this;
        }
        public Builder setTicketPrice(int ticketPrice) {
            this.ticketPrice = ticketPrice;

            return this;
        }

        public MovieSession build() {
            MovieSession movieSession = new MovieSession();
            movieSession.movieId = this.movieId;
            movieSession.date = this.date;
            movieSession.time = this.time;
            movieSession.ticketPrice = this.ticketPrice;
            return movieSession;
        }
    }

    @Override
    public String toString() {
        return "MovieSession{" +
                "movieId=" + movieId +
                ", date=" + date +
                ", time=" + time +
                ", ticketPrice=" + ticketPrice +
                '}';
    }
}
