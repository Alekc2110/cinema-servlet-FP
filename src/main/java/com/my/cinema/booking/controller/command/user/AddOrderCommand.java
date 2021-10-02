package com.my.cinema.booking.controller.command.user;

import com.my.cinema.booking.controller.command.Command;
import com.my.cinema.booking.controller.command.Path;
import com.my.cinema.booking.model.entity.*;
import com.my.cinema.booking.model.enums.Status;
import com.my.cinema.booking.service.MovieService;
import com.my.cinema.booking.service.OrderService;
import com.my.cinema.booking.utils.LoginUserUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.*;

public class AddOrderCommand extends Command {
    private final Logger LOG = Logger.getLogger(AddOrderCommand.class);
    private static final String ORDER_SESSION_ATTR = "order";
    private static final String MOVIE_ATTR = "movie";
    private static final String MOVIE_SESSION_ATTR = "movieSession";

    private OrderService orderService;
    private MovieService movieService;


    public AddOrderCommand(OrderService orderService, MovieService movieService) {
        this.orderService = orderService;
        this.movieService = movieService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.info("create order and return order confirm page");
        List<Seat> orderedSeats = new ArrayList<>();
        List<Ticket> tickets = new ArrayList<>();
        Map<String, String[]> parameterMap = request.getParameterMap();
        Long movieSesId = Long.parseLong(request.getParameter("movieSesId"));
        MovieSession movieSessionById = movieService.findMovieSessionById(movieSesId);
        Movie movie = movieService.findMovieById(movieSessionById.getMovieId());
        LOG.info("get MovieSession from DB: " + movieSessionById);
        LOG.info("get movie from DB: " + movie);
        User loginedUser = LoginUserUtils.getLoginedUser(request.getSession());

        String[] seatsIds = parameterMap.get("seatId");
        if (seatsIds.length > 0) {
            for (String seatId : seatsIds) {
                Seat seatById = orderService.getSeatById(Long.parseLong(seatId));
                orderedSeats.add(seatById);
            }
            Order order = new Order();
            order.setOrderTime(LocalDateTime.now());
            order.setUserId(loginedUser.getId());
            order.setOrderPrice(orderedSeats.size() * movieSessionById.getTicketPrice());
            order.setOrderStatus(Status.BOOKED);
            Long savedOrderId = orderService.saveOrder(order);
            order.setId(savedOrderId);
            LOG.info("create new order and set it to session: " + order);
            orderedSeats.forEach(s -> {
                Ticket ticket = new Ticket.Builder().
                        setMovieSession(movieSessionById).
                        setRow(orderService.getRowById(s.getRowId())).
                        setSeat(s).
                        setOrderId(order.getId()).
                        build();
                tickets.add(ticket);
            });

            order.setTicketList(tickets);
            LOG.info("added ticketList to order: " + tickets);
            HttpSession session = request.getSession();
            request.setAttribute(MOVIE_SESSION_ATTR, movieSessionById);
            request.setAttribute(MOVIE_ATTR, movie);
            session.setAttribute(ORDER_SESSION_ATTR, order);
        }
        return Path.PAGE_CONFIRM_ORDER;
    }
}
