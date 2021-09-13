package com.my.cinema.booking.controller.command.common;

import com.my.cinema.booking.controller.command.Command;
import com.my.cinema.booking.controller.command.Path;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ErrorForbiddenCommand extends Command {
    private final Logger LOGGER = Logger.getLogger(ErrorForbiddenCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("return 403 page");
        response.setStatus(403);
        return Path.PAGE_FORBIDDEN_403;
    }
}

