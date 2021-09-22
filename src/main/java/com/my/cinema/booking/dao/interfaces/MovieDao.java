package com.my.cinema.booking.dao.interfaces;

import com.my.cinema.booking.model.entity.Movie;
import com.my.cinema.booking.model.entity.MovieSession;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MovieDao extends AutoCloseable {

    List<Movie> getAllMovies();

    Optional<Movie> getMovieById(Long movieId);

    List<MovieSession> getMovieSessionsByMovie(Long movieId);

    boolean deleteMovie(Long movieId);

    boolean updateMovie(Movie movie);

    Optional<Long> saveMovie(Movie movie);

    Optional<MovieSession> getMovieSessionById(Long movieSesId);

    boolean updateMovieSession(MovieSession movieSession);

    boolean deleteMovieSessionById(Long MovieSesId);

    Optional<Long> saveMovieSes(MovieSession movieSession);

    void close();

}
