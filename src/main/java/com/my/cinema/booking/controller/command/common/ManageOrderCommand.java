package com.my.cinema.booking.controller.command.common;

import com.my.cinema.booking.controller.command.Command;
import com.my.cinema.booking.controller.command.Path;
import com.my.cinema.booking.controller.command.admin.ManageMoviesCommand;
import com.my.cinema.booking.model.entity.Movie;
import com.my.cinema.booking.model.entity.MovieSession;
import com.my.cinema.booking.model.entity.Seat;
import com.my.cinema.booking.model.enums.Status;
import com.my.cinema.booking.service.MovieService;
import com.my.cinema.booking.service.OrderService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManageOrderCommand extends Command {
    private final Logger LOG = Logger.getLogger(ManageOrderCommand.class);
    private static final String MOVIE_SES_LIST_ATTR = "movieSessionList";
    private static final String ACTIVE_MOVIE = "movie";

    private MovieService movieService;
    private OrderService orderService;

    public ManageOrderCommand(MovieService movieService, OrderService orderService) {
        this.movieService = movieService;
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        if (request.getMethod().equals("GET")) {
            Long movieId = Long.parseLong(request.getParameter("movieId"));
            List<MovieSession> movieSessionList = movieService.findMovieSesByMovieId(movieId);
            Movie movie = movieService.findMovieById(movieId);
            request.setAttribute(ACTIVE_MOVIE, movie);
            request.setAttribute(MOVIE_SES_LIST_ATTR, movieSessionList);
            return Path.PAGE_ORDER_MOVIE_S;
        } else {
            LOG.info("showing available seat list for users");
            Map<Seat, Status> availSeatsMap = new HashMap<>();
            List<Seat> allSeats = orderService.getAllSeats();
//            orderService.findAllBookedSeats()
//            request.setAttribute(ALL_MOVIE_LIST_ATTRIBUTE, allMovies);
            return Path.PAGE_ADMIN_SHOW_MOVIES;

        }


    }
}
