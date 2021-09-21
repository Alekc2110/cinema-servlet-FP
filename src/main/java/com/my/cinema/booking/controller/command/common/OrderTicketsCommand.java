package com.my.cinema.booking.controller.command.common;

import com.my.cinema.booking.controller.command.Command;
import com.my.cinema.booking.controller.command.Path;
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


public class OrderTicketsCommand extends Command {
    private final Logger LOG = Logger.getLogger(OrderTicketsCommand.class);
    private static final String ALL_SEATS_LIST_ATTR = "allSeatsList";
    private static final String MOVIE_ATTR = "movie";
    private static final String MOVIE_SESSION_ATTR = "movieSession";

    private MovieService movieService;
    private OrderService orderService;

    public OrderTicketsCommand(MovieService movieService, OrderService orderService) {
        this.movieService = movieService;
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.info("execute OrderTicketsCommand, return page to make order");
        Long movieSesId = Long.parseLong(request.getParameter("movieSesId"));
        MovieSession movieSessionById = movieService.findMovieSessionById(movieSesId);
        Movie movieById = movieService.findMovieById(movieSessionById.getMovieId());
        List<Seat> allSeats = orderService.getAllSeats();
        List<Seat> allBookedSeats = orderService.findAllBookedSeats(movieSesId);

        allSeats.forEach(seat -> seat.setStatus(Status.FREE));
        allSeats.stream().filter(allBookedSeats::contains).forEach(seat -> seat.setStatus(Status.BOOKED));

        request.setAttribute(ALL_SEATS_LIST_ATTR, allSeats);
        request.setAttribute(MOVIE_ATTR, movieById);
        request.setAttribute(MOVIE_SESSION_ATTR, movieSessionById);
        return Path.PAGE_ORDER_TICKETS;
    }
}
