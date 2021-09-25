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
    private static final String PAGINATION_PARAMETER = "pagination";
    private static final String RECORD_PER_PAGE_ATTRIBUTE = "recordPerPage";
    private static final String PAGE_NUMBERS_ATTRIBUTE = "pageNumbers";

    public ShowTicketsTableCommand(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.info("show bought tickets list in ShowTicketsTableCommand");
        int pageNumber;
        int recordPerPage = 5;
        int startIndex;
        int numberOfPages;
        final User loginedUser = LoginUserUtils.getLoginedUser(request.getSession());
        String sPageNo = request.getParameter(PAGINATION_PARAMETER);
        LOG.info("get number of page " + sPageNo);
        pageNumber = getPageNumber(sPageNo);
        startIndex = (pageNumber * recordPerPage) - recordPerPage;
        int limitIndex = startIndex + recordPerPage;
        final List<Ticket> ticketByUserId = ticketService.findTicketByUserId(loginedUser.getId());
        int totalNumberRecords = ticketByUserId.size();
        if(limitIndex > totalNumberRecords){
            limitIndex = totalNumberRecords;
        }
        LOG.info("users ticket list size: " + totalNumberRecords);
        List<Ticket> ticketsPages = ticketByUserId.subList(startIndex, limitIndex);
        request.setAttribute(TICKET_LIST_ATTRIBUTE, ticketsPages);
        request.setAttribute(RECORD_PER_PAGE_ATTRIBUTE, recordPerPage);
        numberOfPages = totalNumberRecords / recordPerPage;
        if (totalNumberRecords > numberOfPages * recordPerPage) {
            numberOfPages = numberOfPages + 1;
        }
        LOG.info("set request numberOfPages " + numberOfPages);
        request.setAttribute(PAGE_NUMBERS_ATTRIBUTE, numberOfPages);
        return Path.PAGE_SHOW_TICKETS_TABLE;
    }

    private int getPageNumber(String strNumber) {
        try {
            return Integer.parseInt(strNumber);
        } catch (IllegalArgumentException e) {
            LOG.error("caught exception in getPageNumber for pageNumber: " + strNumber);
        }
        return 1;
    }
}
