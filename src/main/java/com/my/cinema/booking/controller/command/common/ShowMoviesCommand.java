package com.my.cinema.booking.controller.command.common;

import com.my.cinema.booking.controller.command.Command;
import com.my.cinema.booking.controller.command.Path;
import com.my.cinema.booking.model.entity.Movie;
import com.my.cinema.booking.service.MovieService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

public class ShowMoviesCommand extends Command {
    private final Logger LOG = Logger.getLogger(ShowMoviesCommand.class);
    private MovieService movieService;
    private static final String MOVIE_LIST_ATTRIBUTE = "movieList";

    public ShowMoviesCommand(MovieService movieService) {
        this.movieService = movieService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Movie> allActiveMovies = movieService.findAllMovies().stream().
                filter(movie -> movie.getMovieSessionList().size()>0).collect(Collectors.toList());
        LOG.info("show movie list in ShowMoviesCommand");
        request.setAttribute(MOVIE_LIST_ATTRIBUTE, allActiveMovies);
        return Path.PAGE_SHOW_ALL_MOVIES;
    }
}
