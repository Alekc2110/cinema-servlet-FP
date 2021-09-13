package com.my.cinema.booking.controller.command.admin;

import com.my.cinema.booking.controller.command.Command;
import com.my.cinema.booking.controller.command.Path;
import com.my.cinema.booking.model.entity.Movie;
import com.my.cinema.booking.service.MovieService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ManageMoviesCommand extends Command {
    private final Logger LOG = Logger.getLogger(ManageMoviesCommand.class);
    private MovieService movieService;
    private static final String ALL_MOVIE_LIST_ATTRIBUTE = "allMoviesList";

    public ManageMoviesCommand(MovieService movieService) {
        this.movieService = movieService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.info("showing movies list for admin");
        List<Movie> allMovies = movieService.findAllMovies();
        request.setAttribute(ALL_MOVIE_LIST_ATTRIBUTE, allMovies);
        return Path.PAGE_ADMIN_SHOW_MOVIES;
    }
}
