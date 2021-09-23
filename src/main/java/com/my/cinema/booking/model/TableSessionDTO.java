package com.my.cinema.booking.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class TableSessionDTO {
    private String movieTitle;
    private LocalDate date;
    private LocalTime time;

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "TableSessionDTO{" +
                "movieTitle='" + movieTitle + '\'' +
                ", date=" + date +
                ", time=" + time +
                '}';
    }
}
