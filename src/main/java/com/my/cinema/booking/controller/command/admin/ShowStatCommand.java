package com.my.cinema.booking.controller.command.admin;

import com.my.cinema.booking.controller.command.Command;
import com.my.cinema.booking.controller.command.Path;
import com.my.cinema.booking.model.entity.MovieSession;
import com.my.cinema.booking.service.MovieService;
import com.my.cinema.booking.service.OrderService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.time.LocalDate;


public class ShowStatCommand extends Command {
    private final Logger LOG = Logger.getLogger(ShowStatCommand.class);
    private OrderService orderService;

    private static final int PERCENTAGE_100 = 100;
    private static final int HALL_CAPACITY = 50;
    private static final String PERCENTAGE_BOOKED_SEATS = "percentage";

    public ShowStatCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.info("showing hall fulfilling for admin");
        LocalDate date = Date.valueOf(request.getParameter("date")).toLocalDate();
        final int countBookedSeat = orderService.getCountBookedSeatByDate(date);
        final float percentage =  countBookedSeat/(float)HALL_CAPACITY*PERCENTAGE_100;
        request.setAttribute(PERCENTAGE_BOOKED_SEATS, String.format("%.0f", percentage));
        return Path.PAGE_ADMIN_ACCOUNT;
    }
}
