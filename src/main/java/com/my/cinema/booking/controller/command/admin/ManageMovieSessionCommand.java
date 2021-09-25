package com.my.cinema.booking.controller.command.admin;

import com.my.cinema.booking.controller.command.Command;
import com.my.cinema.booking.controller.command.Path;
import com.my.cinema.booking.model.entity.MovieSession;
import com.my.cinema.booking.service.MovieService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ManageMovieSessionCommand extends Command {
    private final Logger LOG = Logger.getLogger(ManageMovieSessionCommand.class);
    private MovieService movieService;
    private static final String MOVIE_S_LIST_ATTRIBUTE = "moviesSesList";
    private static final String MOVIE_ID = "movieId";

    public ManageMovieSessionCommand(MovieService movieService) {
        this.movieService = movieService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.info("showing movie sessions list for admin");
        Long movieId;
        String movieIdStr = request.getParameter("movieId");

        if (movieIdStr != null && !movieIdStr.isEmpty()) {
            movieId = Long.parseLong(movieIdStr);
            List<MovieSession> movieSesList = movieService.findMovieSesByMovieId(movieId);
            request.setAttribute(MOVIE_S_LIST_ATTRIBUTE, movieSesList);
            request.setAttribute(MOVIE_ID, movieId);
        } else {
            Object movieIdObj = request.getSession().getAttribute("movieId");
            if (movieIdObj != null) {
                movieId = Long.parseLong(String.valueOf(movieIdObj));
                List<MovieSession> movieSesList = movieService.findMovieSesByMovieId(movieId);
                request.setAttribute(MOVIE_S_LIST_ATTRIBUTE, movieSesList);
                request.setAttribute(MOVIE_ID, movieId);
            }
        }
        return Path.PAGE_ADMIN_MOVIE_SESSION;
    }
}
