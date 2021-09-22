package com.my.cinema.booking.controller.command.user;

import com.my.cinema.booking.controller.command.Command;
import com.my.cinema.booking.controller.command.Path;
import com.my.cinema.booking.model.entity.Movie;
import com.my.cinema.booking.service.MovieService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TimetableUserCommand extends Command {
    private final Logger LOG = Logger.getLogger(TimetableUserCommand.class);
    private MovieService movieService;
    //private static final String MOVIE_LIST_ATTRIBUTE = "movieList";

    public TimetableUserCommand(MovieService movieService) {
        this.movieService = movieService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
//        LOG.info("show timeTable for a week in TimetableUserCommand");
//        List<Movie> today = new ArrayList<>();
//        List<Movie> todayPlus1 = new ArrayList<>();
//        List<Movie> allMovies = movieService.findAllMovies();
//        allMovies.forEach(movie -> {
//            final boolean match = movie.getMovieSessionList().stream().
//                    anyMatch(ms -> ms.getShowTime().getDayOfWeek().equals(LocalDateTime.now().getDayOfWeek()));
//            if(match)
//            today.add(movie);
//        });
////        allMovies.forEach(movie -> {
////            final boolean match = movie.getMovieSessionList().stream().
////                    anyMatch(ms -> ms.getShowTime().getDayOfWeek().equals(LocalDateTime.now().plusDays(1L).getDayOfWeek()));
////            if(match)
////                todayPlus1.add(movie);
////        });
//        movieService.findMoviesByShowTime(LocalDateTime.now());


        //  request.setAttribute(MOVIE_LIST_ATTRIBUTE, allActiveMovies);
        return Path.PAGE_SHOW_MOVIES_TABLE;
    }
}
