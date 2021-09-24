package com.my.cinema.booking.service;

import com.my.cinema.booking.dao.DaoFactory;
import com.my.cinema.booking.dao.interfaces.MovieDao;
import com.my.cinema.booking.dao.interfaces.OrderDao;
import com.my.cinema.booking.exceptions.EmailExistException;
import com.my.cinema.booking.model.entity.Movie;
import com.my.cinema.booking.model.entity.MovieSession;
import com.my.cinema.booking.model.entity.Seat;
import com.my.cinema.booking.model.entity.User;
import com.my.cinema.booking.model.enums.Role;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MovieServiceTest {

    @Mock
    private MovieDao movieDao;

    @Mock
    private DaoFactory factory;

    private MovieService movieService;

    @BeforeEach
    void setUp() {
        setMock(factory);
        movieService = new MovieService();
        when(factory.createMovieDao()).thenReturn(movieDao);
    }

    @AfterEach
    public void resetSingleton() throws Exception {
        Field instance = DaoFactory.class.getDeclaredField("daoFactory");
        instance.setAccessible(true);
        instance.set(null, null);
    }

    @Test
    @DisplayName("should return list of all movies")
    void findAllMoviesTest() {
        List<Movie> movies = Arrays.asList(new Movie.Builder().setTitle("movie1").setDescription("description").build(),
                new Movie.Builder().setTitle("movie2").setDescription("description2").build(),
                new Movie.Builder().setTitle("movie3").setDescription("description3").build());
        when(movieDao.getAllMovies()).thenReturn(movies);

        List<Movie> resultList = movieService.findAllMovies();

        Assertions.assertAll(() -> {
            assertEquals(3L, resultList.size());
            assertEquals("movie1", resultList.get(0).getTitle());
        });
    }

    @Test
    @DisplayName("should return movie by id")
    void findMovieByIdTest() {
        Movie movie = new Movie.Builder().setTitle("movie1").setDescription("description").build();
        Long movieId = 55L;
        movie.setId(movieId);
        when(movieDao.getMovieById(movieId)).thenReturn(java.util.Optional.of(movie));

        Movie result = movieService.findMovieById(movieId);

        Assertions.assertAll(() -> {
            assertEquals(55L, result.getId());
            assertEquals("movie1", result.getTitle());
            assertEquals("description", result.getDescription());
        });
    }


    @Test
    @DisplayName("should return list movie sessions by movie id")
    void findMovieSesByMovieIdTest() {
        Long movieId = 1L;
        List<MovieSession> movieSessions = Arrays.asList(new MovieSession.Builder().
                        setDate(LocalDate.now()).
                        setMovieId(movieId).
                        setTicketPrice(150).
                        setTime(LocalTime.MIDNIGHT).build(),
                new MovieSession.Builder().
                        setDate(LocalDate.now()).
                        setMovieId(movieId).
                        setTicketPrice(250).
                        setTime(LocalTime.NOON).build());
        when(movieDao.getMovieSessionsByMovie(movieId)).thenReturn(movieSessions);

        List<MovieSession> result = movieService.findMovieSesByMovieId(movieId);


        Assertions.assertAll(() -> {
            assertEquals(2L, result.size());
            assertEquals(movieId, result.get(0).getMovieId());
            assertEquals(movieId, result.get(1).getMovieId());
        });
    }


    @Test
    @DisplayName("should delete movie by id")
    void deleteMovieByIdTest() {
        Long movieId = 10L;
        when(movieDao.deleteMovie(movieId)).thenReturn(true);

        boolean result = movieService.deleteMovieById(10L);

        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("should update movie by id")
    void updateMovieTest() {
        Movie movie = new Movie.Builder().setTitle("movie1").setDescription("description").build();
        when(movieDao.updateMovie(movie)).thenReturn(true);

        boolean result = movieService.updateMovie(movie);

        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("should save movie")
    void saveMovieTest() {
        Long savedId = 100L;
        Movie movie = new Movie.Builder().setTitle("movie1").setDescription("description").build();
        when(movieDao.saveMovie(movie)).thenReturn(Optional.of(savedId));

        Long id = movieService.saveMovie(movie);

        Assertions.assertEquals(savedId, id);
    }

    @Test
    @DisplayName("should return movie session by id")
    void findMovieSessionByIdTest() {
        MovieSession movieSession = new MovieSession.Builder().
                setDate(LocalDate.now()).
                setMovieId(1L).
                setTicketPrice(150).
                setTime(LocalTime.MIDNIGHT).build();

        movieSession.setId(55L);
        when(movieDao.getMovieSessionById(55L)).thenReturn(Optional.of(movieSession));

        MovieSession result = movieService.findMovieSessionById(55L);

        Assertions.assertAll(() -> {
            assertEquals(55L, result.getId());
            assertEquals(150, result.getTicketPrice());
            assertEquals(LocalTime.MIDNIGHT, result.getTime());
        });
    }

    @Test
    @DisplayName("should update movie session")
    void updateMovieSessionTest() {
        MovieSession movieSession = new MovieSession.Builder().
                setDate(LocalDate.now()).
                setMovieId(1L).
                setTicketPrice(150).
                setTime(LocalTime.MIDNIGHT).build();
        movieSession.setId(55L);
        when(movieDao.updateMovieSession(movieSession)).thenReturn(true);

        boolean updated = movieService.updateMovieSession(movieSession);

        Assertions.assertTrue(updated);
    }


    @Test
    @DisplayName("should delete movie session")
    void deleteMovieSessionByIdTest() {
        Long savedId = 100L;
        Movie movie = new Movie.Builder().setTitle("movie1").setDescription("description").build();
        when(movieDao.saveMovie(movie)).thenReturn(Optional.of(savedId));

        Long id = movieService.saveMovie(movie);

        Assertions.assertEquals(savedId, id);
    }

    @Test
    @DisplayName("should save movie session")
    void saveMovieSessionTest() {
        MovieSession movieSession = new MovieSession.Builder().
                setDate(LocalDate.now()).
                setMovieId(1L).
                setTicketPrice(150).
                setTime(LocalTime.MIDNIGHT).build();
        Long savedId = 1L;

        when(movieDao.saveMovieSes(movieSession)).thenReturn(Optional.of(savedId));

        Long id = movieService.saveMovieSession(movieSession);

        Assertions.assertEquals(savedId, id);

    }

    @Test
    @DisplayName("should return list movie sessions by date")
    void findMovieSesByDateTest() {
        List<MovieSession> movieSessions = Arrays.asList(new MovieSession.Builder().
                        setDate(LocalDate.now()).
                        setMovieId(1L).
                        setTicketPrice(150).
                        setTime(LocalTime.MIDNIGHT).build(),
                new MovieSession.Builder().
                        setDate(LocalDate.now()).
                        setMovieId(2L).
                        setTicketPrice(250).
                        setTime(LocalTime.NOON).build());
        when(movieDao.getMovieSesByDate(LocalDate.now())).thenReturn(movieSessions);

        List<MovieSession> result = movieService.findMovieSesByDate(LocalDate.now());

        Assertions.assertAll(() -> {
            assertEquals(2L, result.size());
            assertEquals(1L, result.get(0).getMovieId());
            assertEquals(2L, result.get(1).getMovieId());
        });

    }


    private void setMock(DaoFactory mock) {
        Field instance;
        try {
            instance = DaoFactory.class.getDeclaredField("daoFactory");
            instance.setAccessible(true);
            instance.set(instance, mock);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
