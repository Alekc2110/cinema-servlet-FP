package com.my.cinema.booking.controller.command.admin;

import com.my.cinema.booking.controller.command.Command;
import com.my.cinema.booking.service.MovieService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.my.cinema.booking.controller.command.Path.ADMIN_MANAGE_MOVIES;
import static com.my.cinema.booking.controller.command.Path.REDIRECT;

public class DeleteMovieSessionCommand extends Command {
    private final Logger LOG = Logger.getLogger(DeleteMovieSessionCommand.class);
    private static final String DELETE_SUCCESS = "?movieSesSuccessDel=true";
    private static final String DELETE_FALSE = "?movieSesSuccessDel=false";
    private MovieService movieService;

    public DeleteMovieSessionCommand(MovieService movieService) {
        this.movieService = movieService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.info("starts command delete movie session");
        String contextAndServletPath = request.getContextPath() + request.getServletPath();
        Long movieSessionId = Long.parseLong(request.getParameter("movieSesId"));
        boolean deleted = movieService.deleteMovieSessionById(movieSessionId);
        if(deleted){
            return REDIRECT + contextAndServletPath + ADMIN_MANAGE_MOVIES + DELETE_SUCCESS; // try forward to .jsp
        }
        return REDIRECT + contextAndServletPath + ADMIN_MANAGE_MOVIES + DELETE_FALSE;
    }
}
