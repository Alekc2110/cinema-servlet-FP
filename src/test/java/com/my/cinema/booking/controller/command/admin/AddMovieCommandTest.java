package com.my.cinema.booking.controller.command.admin;

import com.my.cinema.booking.controller.command.Command;
import com.my.cinema.booking.controller.command.Path;
import com.my.cinema.booking.service.MovieService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.my.cinema.booking.controller.command.Path.ADMIN_MANAGE_MOVIES;
import static com.my.cinema.booking.controller.command.Path.REDIRECT;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AddMovieCommandTest {
    @Mock
   private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private MovieService movieService;

    private Command addMovieCommand;

    @BeforeEach
    void setUp(){
        addMovieCommand = new AddMovieCommand(movieService);
    }

    @Test
    @DisplayName("should return addMovie.jsp page if method is GET")
    void executeReturnsPageTest() throws IOException, ServletException {
        when(request.getMethod()).thenReturn("GET");
        Assertions.assertEquals(Path.PAGE_ADMIN_ADD_MOVIE, addMovieCommand.execute(request,response));
    }

    @Test
    @DisplayName("should add new movie and redirect with manageMovie command")
    void executeRedirectToCommandTest() throws IOException, ServletException {
        when(request.getMethod()).thenReturn("POST");
        when(request.getContextPath()).thenReturn("");
        when(request.getServletPath()).thenReturn("/cinema");

        Assertions.assertEquals(REDIRECT + "/cinema" + ADMIN_MANAGE_MOVIES + "?successAdd=true", addMovieCommand.execute(request,response));
    }

}
