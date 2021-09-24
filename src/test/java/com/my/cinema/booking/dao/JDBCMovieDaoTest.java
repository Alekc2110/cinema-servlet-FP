package com.my.cinema.booking.dao;

import com.my.cinema.booking.dao.impl.JDBCMovieDao;
import com.my.cinema.booking.dao.interfaces.MovieDao;
import com.my.cinema.booking.model.entity.Movie;
import com.my.cinema.booking.model.entity.MovieSession;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static com.my.cinema.booking.dao.constants.Queries.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JDBCMovieDaoTest {
    @Mock
    private Connection connection;
    @Mock
    private PreparedStatement prepareStatement;
    @Mock
    private Statement statement;
    @Mock
    private ResultSet rs;

    private MovieDao movieDao;

    @BeforeEach
    void setUp(){
        movieDao = new JDBCMovieDao(connection);
    }

    @Test
    @DisplayName("should return list of movies")
    void getAllMoviesTest() throws SQLException {
        when(connection.createStatement()).thenReturn(statement);
        when(statement.executeQuery(GET_ALL_MOVIES)).thenReturn(rs);

        List<Movie> allMovies = movieDao.getAllMovies();

        Assertions.assertTrue(allMovies.isEmpty());
    }

    @Test
    @DisplayName("should return empty list if sql exception happens")
    void getAllMoviesExcTest() throws SQLException {
        when(connection.createStatement()).thenReturn(statement);
        when(statement.executeQuery(GET_ALL_MOVIES)).thenThrow(SQLException.class);

        List<Movie> allMovies = movieDao.getAllMovies();

        Assertions.assertTrue(allMovies.isEmpty());
    }

    @Test
    @DisplayName("should return optional of movie by id")
    void getMovieByIdTest() throws SQLException {
        when(connection.prepareStatement(eq(GET_MOVIE_BY_ID))).thenReturn(prepareStatement);
        when(prepareStatement.executeQuery()).thenReturn(rs);

        Optional<Movie> movieById = movieDao.getMovieById(1L);

        Assertions.assertEquals(Optional.empty(), movieById);
    }

    @Test
    @DisplayName("should delete movie by id")
    void deleteMovieTrueTest() throws SQLException {
        when(connection.prepareStatement(eq(DELETE_MOVIE_BY_ID))).thenReturn(prepareStatement);
        when(prepareStatement.executeUpdate()).thenReturn(1);

        Assertions.assertTrue(movieDao.deleteMovie(1L));
    }

    @Test
    @DisplayName("should return false if could not delete movie by id")
    void deleteMovieFalseTest() throws SQLException {
        when(connection.prepareStatement(eq(DELETE_MOVIE_BY_ID))).thenReturn(prepareStatement);
        when(prepareStatement.executeUpdate()).thenReturn(0);

        Assertions.assertFalse(movieDao.deleteMovie(1L));
    }

    @Test
    @DisplayName("should update movie by id")
    void updateMoviePositiveTest() throws SQLException {
        Movie movie = new Movie.Builder().setTitle("movie1").setDescription("description").build();
        movie.setId(1L);
        when(connection.prepareStatement(eq(UPDATE_MOVIE_BY_ID))).thenReturn(prepareStatement);
        when(prepareStatement.executeUpdate()).thenReturn(1);

        Assertions.assertTrue(movieDao.updateMovie(movie));
    }

    @Test
    @DisplayName("should return false if could not update movie by id")
    void updateMovieNegativeTest() throws SQLException {
        Movie movie = new Movie.Builder().setTitle("movie1").setDescription("description").build();
        movie.setId(1L);
        when(connection.prepareStatement(eq(UPDATE_MOVIE_BY_ID))).thenReturn(prepareStatement);
        when(prepareStatement.executeUpdate()).thenReturn(1);

        Assertions.assertTrue(movieDao.updateMovie(movie));
    }

    @Test
    @DisplayName("should save new movie")
    void saveMovieTest() throws SQLException {
        Movie movie = new Movie.Builder().setTitle("movie1").setDescription("description").build();

        when(connection.prepareStatement(eq(SAVE_MOVIE), anyInt())).thenReturn(prepareStatement);

        when(prepareStatement.executeUpdate()).thenReturn(1);
        when(prepareStatement.getGeneratedKeys()).thenReturn(rs);

        Assertions.assertEquals(Optional.empty(),movieDao.saveMovie(movie));
    }

    @Test
    @DisplayName("should return optional empty if throws exception")
    void saveMovieExcTest() throws SQLException {
        Movie movie = new Movie.Builder().setTitle("movie1").setDescription("description").build();

        when(connection.prepareStatement(eq(SAVE_MOVIE), anyInt())).thenReturn(prepareStatement);

        when(prepareStatement.executeUpdate()).thenReturn(1);
        when(prepareStatement.getGeneratedKeys()).thenThrow(SQLException.class);

        Assertions.assertEquals(Optional.empty(),movieDao.saveMovie(movie));
    }

    @Test
    @DisplayName("should return movie session by id")
    void getMovieSessionByIdTest() throws SQLException {
        when(connection.prepareStatement(eq(GET_MOVIE_SES_BY_ID))).thenReturn(prepareStatement);

        when(prepareStatement.executeQuery()).thenReturn(rs);

        Assertions.assertEquals(Optional.empty(),movieDao.getMovieSessionById(1L));
    }


    @Test
    @DisplayName("should optional of movie session if throws exception ")
    void getMovieSessionByIdExcTest() throws SQLException {
        when(connection.prepareStatement(eq(GET_MOVIE_SES_BY_ID))).thenReturn(prepareStatement);
        when(prepareStatement.executeQuery()).thenThrow(SQLException.class);

        Optional<MovieSession> result = movieDao.getMovieSessionById(1L);

        Assertions.assertEquals(Optional.empty(), result);
    }


    @Test
    @DisplayName("should return true when update movie session by id")
    void updateMovieSessionPositiveTest() throws SQLException {
        MovieSession movieSession = new MovieSession.Builder().
                setMovieId(1L).
                setDate(LocalDate.now()).
                setTime(LocalTime.NOON).
                setTicketPrice(150).
                build();
        movieSession.setId(1L);
        when(connection.prepareStatement(eq(UPDATE_MOVIE_SES_BY_ID))).thenReturn(prepareStatement);
        when(prepareStatement.executeUpdate()).thenReturn(1);

        Assertions.assertTrue(movieDao.updateMovieSession(movieSession));
    }

    @Test
    @DisplayName("should return false if throws exception when update movie session by id")
    void updateMovieSessionNegativeTest() throws SQLException {
        MovieSession movieSession = new MovieSession.Builder().
                setMovieId(1L).
                setDate(LocalDate.now()).
                setTime(LocalTime.NOON).
                setTicketPrice(150).
                build();
        movieSession.setId(1L);
        when(connection.prepareStatement(eq(UPDATE_MOVIE_SES_BY_ID))).thenReturn(prepareStatement);
        when(prepareStatement.executeUpdate()).thenThrow(SQLException.class);

        Assertions.assertFalse(movieDao.updateMovieSession(movieSession));
    }

    @Test
    @DisplayName("should delete movie session by id")
    void deleteMovieSessionByIdPositiveTest() throws SQLException {
        when(connection.prepareStatement(eq(DELETE_MOVIE_SES_BY_ID))).thenReturn(prepareStatement);
        when(prepareStatement.executeUpdate()).thenReturn(1);

        Assertions.assertTrue(movieDao.deleteMovieSessionById(1L));
    }

    @Test
    @DisplayName("should return false if could not delete movie by id")
    void deleteMovieSessionByIdNegativeTest() throws SQLException {
        when(connection.prepareStatement(eq(DELETE_MOVIE_SES_BY_ID))).thenReturn(prepareStatement);
        when(prepareStatement.executeUpdate()).thenReturn(0);

        Assertions.assertFalse(movieDao.deleteMovieSessionById(1L));
    }

    @Test
    @DisplayName("should save new movie session")
    void saveMovieSesTest() throws SQLException {
        MovieSession movieSession = new MovieSession.Builder().
                setMovieId(1L).
                setDate(LocalDate.now()).
                setTime(LocalTime.NOON).
                setTicketPrice(150).
                build();

        when(connection.prepareStatement(eq(SAVE_MOVIE_SESSION), anyInt())).thenReturn(prepareStatement);

        when(prepareStatement.executeUpdate()).thenReturn(1);
        when(prepareStatement.getGeneratedKeys()).thenReturn(rs);

        Assertions.assertEquals(Optional.empty(),movieDao.saveMovieSes(movieSession));
    }


    @Test
    @DisplayName("should return Optional empty if throws exception")
    void saveMovieSesExcTest() throws SQLException {
        MovieSession movieSession = new MovieSession.Builder().
                setMovieId(1L).
                setDate(LocalDate.now()).
                setTime(LocalTime.NOON).
                setTicketPrice(150).
                build();

        when(connection.prepareStatement(eq(SAVE_MOVIE_SESSION), anyInt())).thenReturn(prepareStatement);

        when(prepareStatement.executeUpdate()).thenReturn(1);
        when(prepareStatement.getGeneratedKeys()).thenThrow(SQLException.class);

        Assertions.assertEquals(Optional.empty(),movieDao.saveMovieSes(movieSession));
    }

    @Test
    @DisplayName("should return list of movie sessions by date")
    void getMovieSesByDateTest() throws SQLException {
        when(connection.prepareStatement(eq(GET_MOVIE_SESSIONS_BY_DATE))).thenReturn(prepareStatement);
        when(prepareStatement.executeQuery()).thenReturn(rs);

        List<MovieSession> resultList = movieDao.getMovieSesByDate(LocalDate.now());

        Assertions.assertEquals(0, resultList.size());
    }

    @Test
    @DisplayName("should return empty list if sql exception happens")
    void getMovieSesByDateExcTest() throws SQLException {
        when(connection.prepareStatement(eq(GET_MOVIE_SESSIONS_BY_DATE))).thenReturn(prepareStatement);
        when(prepareStatement.executeQuery()).thenThrow(SQLException.class);

        List<MovieSession> resultList = movieDao.getMovieSesByDate(LocalDate.now());

        Assertions.assertEquals(0, resultList.size());
    }


    @Test
    @DisplayName("should return list of movie sessions by movie id")
    void getMovieSessionsByMovieTest() throws SQLException {
        when(connection.prepareStatement(eq(GET_M_S_BY_MOVIE_ID))).thenReturn(prepareStatement);
        when(prepareStatement.executeQuery()).thenReturn(rs);

        List<MovieSession> resultList = movieDao.getMovieSessionsByMovie(1L);

        Assertions.assertEquals(0, resultList.size());
    }

    @Test
    @DisplayName("should return empty list if sql exception happens")
    void getMovieSessionsByMovieExcTest() throws SQLException {
        when(connection.prepareStatement(eq(GET_M_S_BY_MOVIE_ID))).thenReturn(prepareStatement);
        when(prepareStatement.executeQuery()).thenThrow(SQLException.class);

        List<MovieSession> resultList = movieDao.getMovieSessionsByMovie(1L);

        Assertions.assertTrue(resultList.isEmpty());
    }


}
