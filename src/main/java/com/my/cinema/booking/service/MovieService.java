package com.my.cinema.booking.service;

import com.my.cinema.booking.dao.DaoFactory;
import com.my.cinema.booking.dao.interfaces.MovieDao;
import com.my.cinema.booking.exceptions.EntityNotFoundException;
import com.my.cinema.booking.exceptions.EntitySaveDaoException;
import com.my.cinema.booking.model.entity.Movie;
import com.my.cinema.booking.model.entity.MovieSession;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class MovieService {
    private static final Logger LOG = Logger.getLogger(MovieService.class);

    private DaoFactory factory = DaoFactory.getInstance();

    public List<Movie> findAllMovies() {
        try (MovieDao dao = factory.createMovieDao()) {
            LOG.debug("find all movie showing in cinema");
            return dao.getAllMovies();
        }
    }
    public Movie findMovieById(Long movieId){
        try (MovieDao dao = factory.createMovieDao()) {
            LOG.debug("find movie by movie id: " + movieId);
            Optional<Movie> movieOptional = dao.getMovieById(movieId);
            return movieOptional.orElseThrow(EntityNotFoundException::new);
        }
    }
    public List<MovieSession> findMovieSesByMovieId(Long movieId){
        try (MovieDao dao = factory.createMovieDao()) {
            LOG.debug("find movie sessions by movie id: " + movieId);
            return dao.getMovieSessionsByMovie(movieId);
        }
    }
    public boolean deleteMovieById(Long movieId){
        try (MovieDao dao = factory.createMovieDao()) {
            LOG.debug("delete movie by movie id: " + movieId);
            return dao.deleteMovie(movieId);
        }
    }
    public boolean updateMovie(Movie movie){
        try (MovieDao dao = factory.createMovieDao()) {
            LOG.debug("update movie by movie id");
            return dao.updateMovie(movie);
        }
    }
    public Long saveMovie(Movie movie){
        try (MovieDao dao = factory.createMovieDao()) {
            LOG.debug("save new movie in db: " + movie);
            Optional<Long> savedMovieOpt = dao.saveMovie(movie);
            return savedMovieOpt.orElseThrow(EntitySaveDaoException::new);
        }
    }
    public MovieSession findMovieSessionById(Long msId){
        try (MovieDao dao = factory.createMovieDao()) {
            LOG.debug("find movie session by id: " + msId);
            Optional<MovieSession> movieSesOptional = dao.getMovieSessionById(msId);
            return movieSesOptional.orElseThrow(EntityNotFoundException::new);
        }
    }
    public boolean updateMovieSession(MovieSession movieSession){
        try (MovieDao dao = factory.createMovieDao()) {
            LOG.debug("update movie session by id");
            return dao.updateMovieSession(movieSession);
        }
    }
    public boolean deleteMovieSessionById(Long movieSesId){
        try (MovieDao dao = factory.createMovieDao()) {
            LOG.debug("delete movie session by id: " + movieSesId);
            return dao.deleteMovieSessionById(movieSesId);
        }
    }

    public Long saveMovieSession(MovieSession movieSession){
        try (MovieDao dao = factory.createMovieDao()) {
            LOG.debug("save new movie session in db");
            Optional<Long> savedMovieSesOpt = dao.saveMovieSes(movieSession);
            return savedMovieSesOpt.orElseThrow(EntitySaveDaoException::new);
        }
    }

    public List<MovieSession> findMovieSesByDate(LocalDate date){
        try (MovieDao dao = factory.createMovieDao()) {
            LOG.debug("find movie sessions by date: " + date);
            return dao.getMovieSesByDate(date);
        }
    }

}
