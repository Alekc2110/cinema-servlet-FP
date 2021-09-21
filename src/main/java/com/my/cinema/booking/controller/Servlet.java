package com.my.cinema.booking.controller;

import com.my.cinema.booking.controller.command.Command;
import com.my.cinema.booking.controller.command.CommandContainer;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.my.cinema.booking.controller.command.Path.*;

public class Servlet extends HttpServlet {
    private final Logger LOG = Logger.getLogger(Servlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.info("doGet process");
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.info("doPost process");
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String commandName = getRequestPath(req);
        LOG.info("get command");
        LOG.info("command name: " + commandName + "!!!");
        Command command = CommandContainer.getCommand(commandName);
        String contextAndServletPath = req.getContextPath() + req.getServletPath();
        if (command == null) {
            LOG.info("command is null redirect home page");
            resp.sendRedirect(contextAndServletPath + HOME);
        } else {
            LOG.info("execute command: " + commandName + "!!!");
            String nextPage = command.execute(req, resp);
            if (isRedirect(nextPage)) {
                LOG.info("redirect page " + nextPage);
                resp.sendRedirect(nextPage.replaceAll(REDIRECT, EMPTY_STR));
            } else {
                LOG.info("forward page " + nextPage);
                req.getRequestDispatcher(nextPage).forward(req, resp);
            }
        }
    }

    private String getRequestPath(HttpServletRequest request) {
        String pathURI = request.getRequestURI();
        LOG.info( "pathURI: " + pathURI);
        String servletPath = request.getServletPath();
        LOG.info("servletPath: " + servletPath);
        String regex = ".*" + servletPath;
        return pathURI.replaceAll(regex, EMPTY_STR);
    }

    private boolean isRedirect(String string) {
        return string.contains(REDIRECT);
    }
}
