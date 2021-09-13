package com.my.cinema.booking.controller.command.common;

import com.my.cinema.booking.controller.command.Command;
import com.my.cinema.booking.controller.command.Path;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterUserCommand extends Command {
    private final Logger LOG = Logger.getLogger(RegisterUserCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.info("return register user page");
        return Path.PAGE_REGISTER_USER;
    }
}
