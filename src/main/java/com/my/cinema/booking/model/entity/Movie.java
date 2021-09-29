package com.my.cinema.booking.model.entity;

import java.util.List;

public class Movie extends Entity {
    private String title;
    private String description;
    private String director;
    private String year;
    private String country;
    private String photoUrl;
    private List<MovieSession> movieSessionList;

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public List<MovieSession> getMovieSessionList() {
        return movieSessionList;
    }

    public void setMovieSessionList(List<MovieSession> movieSessionList) {
        this.movieSessionList = movieSessionList;
    }

    public static class Builder {
        private String title;
        private String description;
        private String photoUrl;
        private String director;
        private String year;
        private String country;

        public Builder setTitle(String title) {
            this.title = title;

            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;

            return this;
        }
        public Builder setPhotoUrl(String photoUrl) {
            this.photoUrl = photoUrl;

            return this;
        }

        public Builder setDirector(String director) {
            this.director = director;

            return this;
        }
        public Builder setCountry(String country) {
            this.country = country;

            return this;
        }
        public Builder setYear(String year) {
            this.year = year;

            return this;
        }

        public Movie build() {
            Movie movie = new Movie();
            movie.title = this.title;
            movie.description = this.description;
            movie.photoUrl = this.photoUrl;
            movie.director = this.director;
            movie.country = this.country;
            movie.year = this.year;
            return movie;
        }
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", director='" + director + '\'' +
                ", year='" + year + '\'' +
                ", country='" + country + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                ", movieSessionList=" + movieSessionList +
                '}';
    }
}
