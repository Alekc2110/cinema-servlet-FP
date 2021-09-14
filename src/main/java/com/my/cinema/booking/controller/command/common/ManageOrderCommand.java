package com.my.cinema.booking.controller.command.common;

import com.my.cinema.booking.controller.command.Command;
import com.my.cinema.booking.controller.command.Path;
import com.my.cinema.booking.model.entity.Movie;
import com.my.cinema.booking.model.entity.MovieSession;
import com.my.cinema.booking.service.MovieService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ManageOrderCommand extends Command {
    private final Logger LOG = Logger.getLogger(ManageOrderCommand.class);
    private static final String MOVIE_SES_LIST_ATTR = "movieSessionList";
    private static final String ACTIVE_MOVIE = "movie";

    private MovieService movieService;


    public ManageOrderCommand(MovieService movieService) {
        this.movieService = movieService;

    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.info("execute ManageOrderCommand, return page order movie sessions");
        Long movieId = Long.parseLong(request.getParameter("movieId"));
        List<MovieSession> movieSessionList = movieService.findMovieSesByMovieId(movieId);
        Movie movie = movieService.findMovieById(movieId);
        request.setAttribute(ACTIVE_MOVIE, movie);
        request.setAttribute(MOVIE_SES_LIST_ATTR, movieSessionList);
        return Path.PAGE_ORDER_MOVIE_S;
    }
}
