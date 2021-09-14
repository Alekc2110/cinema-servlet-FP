package com.my.cinema.booking.model.entity;


import java.time.LocalDateTime;
import java.util.List;


public class MovieSession extends Entity {

    private Long movieId;
    private LocalDateTime showTime;
    private int ticketPrice;


    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public LocalDateTime getShowTime() {
        return showTime;
    }

    public void setShowTime(LocalDateTime showTime) {
        this.showTime = showTime;
    }

    public int getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(int ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public static class Builder {

        private Long movieId;
        private LocalDateTime showTime;
        private int ticketPrice;

        public Builder setMovieId(Long movieId) {
            this.movieId = movieId;

            return this;
        }
        public Builder setShowTime(LocalDateTime showTime) {
            this.showTime = showTime;

            return this;
        }
        public Builder setTicketPrice(int ticketPrice) {
            this.ticketPrice = ticketPrice;

            return this;
        }


        public MovieSession build() {
            MovieSession movieSession = new MovieSession();
            movieSession.movieId = this.movieId;
            movieSession.showTime = this.showTime;
            movieSession.ticketPrice = this.ticketPrice;
            return movieSession;
        }
    }

    @Override
    public String toString() {
        return "MovieSession{" +
                "movieId=" + movieId +
                ", showTime=" + showTime +
                ", ticketPrice=" + ticketPrice +
                '}';
    }
}
