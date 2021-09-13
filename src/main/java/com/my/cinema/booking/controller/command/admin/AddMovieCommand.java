package com.my.cinema.booking.controller.command.admin;

import com.my.cinema.booking.controller.command.Command;
import com.my.cinema.booking.controller.command.Path;
import com.my.cinema.booking.exceptions.EntitySaveDaoException;
import com.my.cinema.booking.model.entity.Movie;
import com.my.cinema.booking.service.MovieService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.my.cinema.booking.controller.command.Path.*;


public class AddMovieCommand extends Command {
    private final Logger LOG = Logger.getLogger(AddMovieCommand.class);
    private static final String ADD_SUCCESS = "?successAdd=true";
    private static final String ADD_FALSE = "?successAdd=false";
    private MovieService movieService;

    public AddMovieCommand(MovieService movieService) {
        this.movieService = movieService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.info("starts command add new movie");
        String contextAndServletPath = request.getContextPath() + request.getServletPath();
        if (request.getMethod().equals("GET")) {
            return Path.PAGE_ADMIN_ADD_MOVIE;
        } else {
            Movie newMovie = new Movie.Builder().
                    setTitle(request.getParameter("title")).
                    setDescription(request.getParameter("description")).
                    setPhotoUrl(request.getParameter("photo")).
                    setDirector(request.getParameter("director")).
                    setCountry(request.getParameter("country")).
                    setYear(request.getParameter("year")).
                    build();
            try {
                Long savedMovieId = movieService.saveMovie(newMovie);
                LOG.info("saved new movie with id: " + savedMovieId);
            } catch (EntitySaveDaoException e) {
                LOG.error("Exception when save new movie: " + newMovie);
                return REDIRECT + contextAndServletPath + ADMIN_MANAGE_MOVIES + ADD_FALSE;
            }

            return REDIRECT + contextAndServletPath + ADMIN_MANAGE_MOVIES + ADD_SUCCESS;
        }

    }
}