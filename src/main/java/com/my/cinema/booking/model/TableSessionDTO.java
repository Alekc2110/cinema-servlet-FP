package com.my.cinema.booking.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class TableSessionDTO {
    private Long id;
    private String movieTitle;
    private LocalDate date;
    private LocalTime time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public static class Builder {
        private Long id;
        private String movieTitle;
        private LocalDate date;
        private LocalTime time;

        public TableSessionDTO.Builder setId(Long id) {
            this.id = id;

            return this;
        }

        public TableSessionDTO.Builder setMovieTitle(String movieTitle) {
            this.movieTitle = movieTitle;

            return this;
        }

        public TableSessionDTO.Builder setDate(LocalDate date) {
            this.date = date;

            return this;
        }

        public TableSessionDTO.Builder setTime(LocalTime time) {
            this.time = time;

            return this;
        }

        public TableSessionDTO build() {
            TableSessionDTO tableSessionDTO = new TableSessionDTO();
            tableSessionDTO.id = this.id;
            tableSessionDTO.movieTitle = this.movieTitle;
            tableSessionDTO.date = this.date;
            tableSessionDTO.time = this.time;
            return tableSessionDTO;
        }
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
