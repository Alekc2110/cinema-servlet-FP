package com.my.cinema.booking.controller.command.user;

import com.my.cinema.booking.controller.command.Command;
import com.my.cinema.booking.controller.command.Path;
import com.my.cinema.booking.model.entity.Order;
import com.my.cinema.booking.service.OrderService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CancelOrderCommand extends Command {
    private final Logger LOG = Logger.getLogger(CancelOrderCommand.class);

    private OrderService orderService;

    public CancelOrderCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.info("try to cancel order and delete order from db and session");
        HttpSession session = request.getSession();
        Order order = (Order) session.getAttribute("order");
        session.removeAttribute("order");
        orderService.deleteOrder(order.getId());
        return Path.PAGE_HOME;
    }
}