package com.my.cinema.booking.controller.command.user;

import com.my.cinema.booking.controller.command.Command;
import com.my.cinema.booking.controller.command.Path;
import com.my.cinema.booking.model.entity.Ticket;
import com.my.cinema.booking.model.entity.User;
import com.my.cinema.booking.service.TicketService;
import com.my.cinema.booking.utils.LoginUserUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowTicketsTableCommand extends Command {
    private final Logger LOG = Logger.getLogger(ShowTicketsTableCommand.class);
    private TicketService ticketService;
    private static final String TICKET_LIST_ATTRIBUTE = "ticketList";

    public ShowTicketsTableCommand(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.info("show bought tickets list in ShowTicketsTableCommand");
        final User loginedUser = LoginUserUtils.getLoginedUser(request.getSession());
        final List<Ticket> ticketByUserId = ticketService.findTicketByUserId(loginedUser.getId());
        request.setAttribute(TICKET_LIST_ATTRIBUTE, ticketByUserId);
        return Path.PAGE_SHOW_TICKETS_TABLE;
    }
}
