package com.my.cinema.booking.dao.impl;

import com.my.cinema.booking.dao.interfaces.MovieDao;
import com.my.cinema.booking.dao.mapper.Mapper;
import com.my.cinema.booking.dao.mapper.MovieMapper;
import com.my.cinema.booking.dao.mapper.MovieSessionMapper;
import com.my.cinema.booking.model.entity.Movie;
import com.my.cinema.booking.model.entity.MovieSession;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.*;

import static com.my.cinema.booking.dao.constants.Queries.*;

public class JDBCMovieDao implements MovieDao {
    private static final Logger LOG = Logger.getLogger(JDBCMovieDao.class);
    private Connection connection;

    public JDBCMovieDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Movie> getAllMovies() {
        List<Movie> allMovies = new ArrayList<>();
        try (Statement st = connection.createStatement()) {
            connection.setAutoCommit(false);
            ResultSet rs = st.executeQuery(GET_ALL_MOVIES);
            Mapper<Movie> MovieMapper = new MovieMapper();
            LOG.debug("Executed query " + GET_ALL_MOVIES);
            while (rs.next()) {
                Movie movie = MovieMapper.getEntity(rs);
                allMovies.add(movie);
            }
            allMovies.forEach(movie -> {
                List<MovieSession> movieSessionsByMovie = getMovieSessionsByMovie(movie.getId());
                movie.setMovieSessionList(movieSessionsByMovie);
            });
            connection.commit();
            return allMovies;
        } catch (SQLException e) {
            LOG.error("SQLException when findAll in JdbcMovieDao", e);
            try {
                connection.rollback();
            } catch (SQLException ex) {
                System.out.println("Exception when trying to rollback:" + ex.getMessage());
            }
            return allMovies;
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                LOG.error("SQLException when setAutoCommit in JdbcMovieDao ", e);
            }
        }
    }

    @Override
    public Optional<Movie> getMovieById(Long movieId) {
        Movie movie = null;
        try (PreparedStatement ps = connection.prepareStatement(GET_MOVIE_BY_ID)) {
            ps.setLong(1, movieId);
            final ResultSet rs = ps.executeQuery();
            LOG.debug("Executed query: " + GET_MOVIE_BY_ID);
            Mapper<Movie> movieMapper = new MovieMapper();
            while (rs.next()) {
                LOG.debug("check if rs has next");
                movie = movieMapper.getEntity(rs);
            }
            return Optional.ofNullable(movie);
        } catch (SQLException e) {
            LOG.error("SQLException in 'getMovieSessionsByMovie' in JdbcMovieDao", e);
            return Optional.empty();
        }
    }


    @Override
    public boolean deleteMovie(Long movieId) {
        try (PreparedStatement ps = connection.prepareStatement(DELETE_MOVIE_BY_ID)) {
            ps.setLong(1, movieId);
            if (ps.executeUpdate() != 1) {
                return false;
            }
        } catch (SQLException e) {
            LOG.info("SQLException in deleteMovie MovieDao: " + e);
            return false;
        }
        return true;
    }

    @Override
    public boolean updateMovie(Movie movie) {
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_MOVIE_BY_ID)) {
            ps.setString(1, movie.getTitle());
            ps.setString(2, movie.getDescription());
            ps.setString(3, movie.getPhotoUrl());
            ps.setString(4, movie.getDirector());
            ps.setString(5, movie.getCountry());
            ps.setString(6, movie.getYear());
            ps.setLong(7, movie.getId());
            if (ps.executeUpdate() != 1) {
                return false;
            }
        } catch (SQLException e) {
            LOG.info("SQLException in updateMovie MovieDao: " + e);
            return false;
        }
        return true;
    }

    @Override
    public Optional<Long> saveMovie(Movie movie) {
        ResultSet generatedKey = null;
        try (PreparedStatement ps = connection.prepareStatement(SAVE_MOVIE, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, movie.getTitle());
            ps.setString(2, movie.getDescription());
            ps.setString(3, movie.getPhotoUrl());
            ps.setString(4, movie.getDirector());
            ps.setString(5, movie.getCountry());
            ps.setString(6, movie.getYear());
            if (ps.executeUpdate() != 1) {
                return Optional.empty();
            }
            generatedKey = ps.getGeneratedKeys();
            if (generatedKey.next()) {
                Long id = generatedKey.getLong(1);
                return Optional.of(id);
            }
        } catch (SQLException e) {
            LOG.info("SQLException in 'saveMovie' JDBCMovieDao: " + e);
            return Optional.empty();
        } finally {
            try {
                if (generatedKey != null) generatedKey.close();
            } catch (SQLException e) {
                LOG.info("SQLException when closing ResultSet in 'saveMovie': " + e);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<MovieSession> getMovieSessionById(Long movieSesId) {
        MovieSession movieSession = null;
        try (PreparedStatement ps = connection.prepareStatement(GET_MOVIE_SES_BY_ID)) {
            ps.setLong(1, movieSesId);
            final ResultSet rs = ps.executeQuery();
            LOG.debug("Executed query: " + GET_MOVIE_SES_BY_ID);
            Mapper<MovieSession> movieSesMapperMapper = new MovieSessionMapper();
            while (rs.next()) {
                LOG.debug("check if rs has next");
                movieSession = movieSesMapperMapper.getEntity(rs);
            }
            return Optional.ofNullable(movieSession);
        } catch (SQLException e) {
            LOG.error("SQLException in 'getMovieSessionsByMovie' in JdbcMovieDao", e);
            return Optional.empty();
        }
    }

    @Override
    public boolean updateMovieSession(MovieSession movieSession) {
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_MOVIE_SES_BY_ID)) {
            ps.setLong(1, movieSession.getMovieId());
            ps.setTimestamp(2, Timestamp.valueOf(movieSession.getShowTime()), Calendar.getInstance(TimeZone.getDefault()));
            ps.setLong(3, movieSession.getTicketPrice());
            ps.setLong(4, movieSession.getId());
            if (ps.executeUpdate() != 1) {
                return false;
            }
        } catch (SQLException e) {
            LOG.info("SQLException in updateMovieSession MovieDao: " + e);
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteMovieSessionById(Long movieSesId) {
        try (PreparedStatement ps = connection.prepareStatement(DELETE_MOVIE_SES_BY_ID)) {
            ps.setLong(1, movieSesId);
            if (ps.executeUpdate() != 1) {
                return false;
            }
        } catch (SQLException e) {
            LOG.info("SQLException in deleteMovieSession MovieDao: " + e);
            return false;
        }
        return true;
    }

    @Override
    public Optional<Long> saveMovieSes(MovieSession movieSession) {
        ResultSet generatedKey = null;
        try (PreparedStatement ps = connection.prepareStatement(SAVE_MOVIE_SESSION, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setLong(1, movieSession.getMovieId());
            ps.setTimestamp(2, Timestamp.valueOf(movieSession.getShowTime()), Calendar.getInstance(TimeZone.getDefault()));
            ps.setInt(3, movieSession.getTicketPrice());
            if (ps.executeUpdate() != 1) {
                return Optional.empty();
            }
            generatedKey = ps.getGeneratedKeys();
            if (generatedKey.next()) {
                Long id = generatedKey.getLong(1);
                return Optional.of(id);
            }
        } catch (SQLException e) {
            LOG.info("SQLException in 'saveMovieSession' JDBCMovieDao: " + e);
            return Optional.empty();
        } finally {
            try {
                if (generatedKey != null) generatedKey.close();
            } catch (SQLException e) {
                LOG.info("SQLException when closing ResultSet in 'saveMovieSession': " + e);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<MovieSession> getMovieSessionsByMovie(Long movieId) {
        List<MovieSession> movieSessions = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(GET_M_S_BY_MOVIE_ID)) {
            ps.setLong(1, movieId);
            final ResultSet rs = ps.executeQuery();
            LOG.debug("Executed query: " + GET_M_S_BY_MOVIE_ID);
            Mapper<MovieSession> MovieSesMapper = new MovieSessionMapper();
            while (rs.next()) {
                LOG.debug("check if rs has next");
                MovieSession movieSession = MovieSesMapper.getEntity(rs);
                movieSessions.add(movieSession);
            }
            return movieSessions;
        } catch (SQLException e) {
            LOG.error("SQLException in 'getMovieSessionsByMovie' in JdbcMovieDao", e);
            return movieSessions;
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
