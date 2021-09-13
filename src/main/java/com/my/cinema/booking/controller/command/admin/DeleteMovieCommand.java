package com.my.cinema.booking.controller.command.admin;

import com.my.cinema.booking.controller.command.Command;
import com.my.cinema.booking.service.MovieService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.my.cinema.booking.controller.command.Path.ADMIN_MANAGE_MOVIES;
import static com.my.cinema.booking.controller.command.Path.REDIRECT;

public class DeleteMovieCommand extends Command {
    private final Logger LOG = Logger.getLogger(DeleteMovieCommand.class);
    private static final String DELETE_SUCCESS = "?successDel=true";
    private static final String DELETE_FALSE = "?successDel=false";
    private MovieService movieService;

    public DeleteMovieCommand(MovieService movieService) {
        this.movieService = movieService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.info("starts command delete movie");
        String contextAndServletPath = request.getContextPath() + request.getServletPath();
        Long movieId = Long.parseLong(request.getParameter("movieId"));
        boolean deleted = movieService.deleteMovieById(movieId);
        if(deleted){
            return REDIRECT + contextAndServletPath + ADMIN_MANAGE_MOVIES + DELETE_SUCCESS;
        }
        return REDIRECT + contextAndServletPath + ADMIN_MANAGE_MOVIES + DELETE_FALSE;
    }
}
