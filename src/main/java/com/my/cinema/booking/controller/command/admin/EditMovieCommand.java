package com.my.cinema.booking.controller.command.admin;

import com.my.cinema.booking.controller.command.Command;
import com.my.cinema.booking.controller.command.Path;
import com.my.cinema.booking.exceptions.EntityNotFoundException;
import com.my.cinema.booking.model.entity.Movie;
import com.my.cinema.booking.service.MovieService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.my.cinema.booking.controller.command.Path.*;


public class EditMovieCommand extends Command {
    private final Logger LOG = Logger.getLogger(EditMovieCommand.class);
    private MovieService movieService;
    private static final String EDIT_MOVIE_ATTRIBUTE = "movieEdit";
    private static final String UPDATE_SUCCESS = "?successUpdate=true";
    private static final String UPDATE_FALSE = "?successUpdate=false";
    private static final String BAD_INPUT = "?badInput=true";

    public EditMovieCommand(MovieService movieService) {
        this.movieService = movieService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String contextAndServletPath = request.getContextPath() + request.getServletPath();
        LOG.info("starts command edit movie");
        if (request.getMethod().equals("GET")) {
            Long movieId = Long.parseLong(request.getParameter("movieId"));
            LOG.info("request method-get return edit page for movie id: " + movieId);
            Movie movieToEdit = movieService.findMovieById(movieId);
            request.setAttribute(EDIT_MOVIE_ATTRIBUTE, movieToEdit);
            return Path.PAGE_ADMIN_EDIT_MOVIE;
        } else {
            Long movieId = Long.parseLong(request.getParameter("movieId"));
            LOG.info("try to update movie with id: " + movieId);
            try {
                Movie movieToEdit = movieService.findMovieById(movieId);
                Movie edited =  new Movie.Builder().
                        setTitle(request.getParameter("title")).
                        setDescription(request.getParameter("description")).
                        setPhotoUrl(request.getParameter("photo")).
                        setDirector(request.getParameter("director")).
                        setCountry(request.getParameter("country")).
                        setYear(request.getParameter("year")).
                        build();
                edited.setId(movieToEdit.getId());
                boolean updated = movieService.updateMovie(edited);
                LOG.info("returned boolean updated: " + updated);
                if(updated){
                    return REDIRECT + contextAndServletPath + ADMIN_MANAGE_MOVIES + UPDATE_SUCCESS;
                }
            } catch (EntityNotFoundException e){
                LOG.error("could not find movie with Id: " + movieId);
               return REDIRECT + contextAndServletPath + ADMIN_MANAGE_MOVIES + BAD_INPUT;
            }
        }
        return REDIRECT + contextAndServletPath + ADMIN_MANAGE_MOVIES + UPDATE_FALSE;
    }
}
