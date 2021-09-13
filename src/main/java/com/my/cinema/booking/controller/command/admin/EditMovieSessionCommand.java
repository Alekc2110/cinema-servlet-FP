package com.my.cinema.booking.controller.command.admin;

import com.my.cinema.booking.controller.command.Command;
import com.my.cinema.booking.controller.command.Path;
import com.my.cinema.booking.exceptions.EntityNotFoundException;
import com.my.cinema.booking.model.entity.Movie;
import com.my.cinema.booking.model.entity.MovieSession;
import com.my.cinema.booking.service.MovieService;
import com.my.cinema.booking.utils.Validator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.my.cinema.booking.controller.command.Path.ADMIN_MANAGE_MOVIE_SES;
import static com.my.cinema.booking.controller.command.Path.REDIRECT;

public class EditMovieSessionCommand extends Command {
    private final Logger LOG = Logger.getLogger(EditMovieSessionCommand.class);
    private static final String EDIT_MOVIE_SES_ATT = "movieSesEdit";
    private static final String MOVIE_TITLE = "movieTitle";
    private static final String UPDATE_SUCCESS = "?movSessionSuccessUpdate=true";
    private static final String UPDATE_FALSE = "?movSessionSuccessUpdate=false";
    private static final String BAD_INPUT = "?movSessionBadInput=true";
    private MovieService movieService;

    public EditMovieSessionCommand(MovieService movieService) {
        this.movieService = movieService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String contextAndServletPath = request.getContextPath() + request.getServletPath();
        if (request.getMethod().equals("GET")) {
            Long movSesId = Long.parseLong(request.getParameter("id"));
            LOG.info("request method-get return edit page for movie session id: " + movSesId);
            MovieSession movieSesById = movieService.findMovieSessionById(movSesId);
            Movie movieById = movieService.findMovieById(movieSesById.getMovieId());
            request.setAttribute(EDIT_MOVIE_SES_ATT, movieSesById);
            request.setAttribute(MOVIE_TITLE, movieById.getTitle());
            return Path.PAGE_ADMIN_EDIT_MOVIE_S;
        } else {
            Long movSesId = Long.parseLong(request.getParameter("movieSesId"));
            LOG.info("try to update movie session with id: " + movSesId);
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                MovieSession movieSesById = movieService.findMovieSessionById(movSesId);
                String showTime = request.getParameter("show_time");
                String price = request.getParameter("price");
                if (Validator.isCorrectTimeStamp(showTime) && Validator.isCorrectPrice(price)) {
                    MovieSession edited = new MovieSession.Builder().
                            setMovieId(movieSesById.getMovieId()).
                            setShowTime(LocalDateTime.parse(showTime, formatter)).
                            setTicketPrice(Integer.parseInt(price)).
                            build();
                    edited.setId(movieSesById.getId());
                    boolean updated = movieService.updateMovieSession(edited);
                    LOG.info("returned boolean updated: " + updated);
                    request.getSession().setAttribute("movieId", movieSesById.getMovieId());
                    if (updated) {
                        return REDIRECT + contextAndServletPath + ADMIN_MANAGE_MOVIE_SES + UPDATE_SUCCESS;
                    }
                }
                request.getSession().setAttribute("movieId", movieSesById.getMovieId());
            } catch (EntityNotFoundException e) {
                LOG.error("could not find movie session with Id: " + movSesId);
                return REDIRECT + contextAndServletPath + ADMIN_MANAGE_MOVIE_SES + BAD_INPUT;
            }
        }
        return REDIRECT + contextAndServletPath + ADMIN_MANAGE_MOVIE_SES + UPDATE_FALSE;
    }
}
