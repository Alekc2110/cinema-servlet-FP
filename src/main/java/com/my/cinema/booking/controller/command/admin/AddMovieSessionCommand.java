package com.my.cinema.booking.controller.command.admin;

import com.my.cinema.booking.controller.command.Command;
import com.my.cinema.booking.controller.command.Path;
import com.my.cinema.booking.exceptions.EntitySaveDaoException;
import com.my.cinema.booking.model.entity.MovieSession;
import com.my.cinema.booking.service.MovieService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.my.cinema.booking.controller.command.Path.*;

public class AddMovieSessionCommand extends Command {
    private final Logger LOG = Logger.getLogger(AddMovieSessionCommand.class);
    private static final String ADD_SUCCESS = "?successAdd=true";
    private static final String ADD_FALSE = "?successAdd=false";
    private MovieService movieService;

    public AddMovieSessionCommand(MovieService movieService) {
        this.movieService = movieService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.info("starts command add new movie session");
        Long movieId = Long.valueOf(request.getParameter("movie_id"));
        request.setAttribute("movie_id", movieId);
        if (request.getMethod().equals("GET")) {
            return Path.PAGE_ADMIN_ADD_MOVIE_S;
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            MovieSession newMovieSession = new MovieSession.Builder().
                    setMovieId(Long.parseLong(request.getParameter("movie_id"))).
                    setShowTime(LocalDateTime.parse(request.getParameter("show_time"), formatter)).
                    setTicketPrice(Integer.parseInt(request.getParameter("price"))).
                    build();
            try {
                Long savedMovieSesId = movieService.saveMovieSession(newMovieSession);
                request.setAttribute("savedMovieSesId", savedMovieSesId);
                LOG.info("saved new movie session with id: " + savedMovieSesId);
            } catch (EntitySaveDaoException e) {
                LOG.error("Exception when save new movie session: " + newMovieSession);
                return PAGE_ADMIN_MOVIE_SESSION;
            }

            return PAGE_ADMIN_MOVIE_SESSION;
        }

    }
}