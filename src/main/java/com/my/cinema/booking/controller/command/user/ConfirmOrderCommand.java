package com.my.cinema.booking.controller.command.user;

import com.my.cinema.booking.controller.command.Command;
import com.my.cinema.booking.controller.command.Path;
import com.my.cinema.booking.model.entity.MovieSession;
import com.my.cinema.booking.model.entity.Order;
import com.my.cinema.booking.model.entity.Ticket;
import com.my.cinema.booking.model.enums.Status;
import com.my.cinema.booking.service.MovieService;
import com.my.cinema.booking.service.OrderService;
import com.my.cinema.booking.service.TicketService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ConfirmOrderCommand extends Command {
    private final Logger LOG = Logger.getLogger(ConfirmOrderCommand.class);
    private static final String ALREADY_BOOKED_SEAT = "?bookedSeats=true";
    private static final String SAVE_ORDER_FALSE = "?orderFalse=true";

    private TicketService ticketService;
    private OrderService orderService;

    public ConfirmOrderCommand(TicketService ticketService, OrderService orderService) {
        this.ticketService = ticketService;
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Long movieSesId = Long.parseLong(request.getParameter("movieSesId"));
        LOG.info("try to confirm order and save tickets");
        List<Ticket> ticketsBySession = ticketService.findTicketsBySession(movieSesId);
        LOG.info("tickets list by session: "+ ticketsBySession);
        HttpSession session = request.getSession();
        Order order = (Order) session.getAttribute("order");
        if (ticketsBySession.size() > 0) {
            if (checkTicketsToBook(ticketsBySession, order))
                return Path.PAGE_ORDER_TICKETS + ALREADY_BOOKED_SEAT;
        }
        order.setOrderStatus(Status.CONFIRMED);
        if (orderService.updateOrder(order)) {
            boolean ticketsSaved = ticketService.saveTickets(order.getTicketList());
            boolean savedBookedSeats = orderService.saveSessionBookSeats(movieSesId, order.getTicketList());
            if (ticketsSaved && savedBookedSeats)
                return Path.PAGE_HOME;
        }

        return Path.PAGE_ORDER_TICKETS + SAVE_ORDER_FALSE;
    }

    private boolean checkTicketsToBook(List<Ticket> tickets, Order order) {
        return order.getTicketList().stream().anyMatch(tickets::contains);

    }
}
