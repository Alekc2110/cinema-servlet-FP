package com.my.cinema.booking.controller.command.common;

import com.my.cinema.booking.controller.command.Command;
import com.my.cinema.booking.controller.command.Path;
import com.my.cinema.booking.model.entity.Movie;
import com.my.cinema.booking.service.MovieService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowMovieDetailCommand extends Command {
    private final Logger LOG = Logger.getLogger(ShowMovieDetailCommand.class);
    private MovieService movieService;
    private static final String MOVIE_ATTRIBUTE = "movie";

    public ShowMovieDetailCommand(MovieService movieService) {
        this.movieService = movieService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Long movieId = Long.parseLong(request.getParameter("id"));
            Movie movieById = movieService.findMovieById(movieId);
            LOG.info("show movie details in ShowMoviesCommand");
            request.setAttribute(MOVIE_ATTRIBUTE, movieById);
        return Path.PAGE_MOVIE_INFO;
    }
}
