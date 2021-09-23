package com.my.cinema.booking.controller.command.user;

import com.my.cinema.booking.controller.command.Command;
import com.my.cinema.booking.controller.command.Path;
import com.my.cinema.booking.model.TableSessionDTO;
import com.my.cinema.booking.model.entity.Movie;
import com.my.cinema.booking.model.entity.MovieSession;
import com.my.cinema.booking.service.MovieService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TimetableUserCommand extends Command {
    private final Logger LOG = Logger.getLogger(TimetableUserCommand.class);
    private MovieService movieService;
    private static final String MOVIE_SES_LIST_TODAY = "movieListNow";
    private static final String MOVIE_SES_LIST_TOM = "movieListTomorrow";
    private static final String MOVIE_SES_LIST_NOW2 = "movieListNowPlTwo";
    private static final String MOVIE_SES_LIST_NOW3 = "movieListNowPlThree";
    private static final String MOVIE_SES_LIST_NOW4 = "movieListNowPlFour";
    private static final String MOVIE_SES_LIST_NOW5 = "movieListNowPlFive";
    private static final String MOVIE_SES_LIST_NOW6 = "movieListNowPlSix";

    public TimetableUserCommand(MovieService movieService) {
        this.movieService = movieService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.info("show timeTable for a week in TimetableUserCommand");
        final List<TableSessionDTO> timeTableNow = getTimeTableByDate(LocalDate.now());
        final List<TableSessionDTO> timeTableTomorrow = getTimeTableByDate(LocalDate.now().plusDays(1L));
        final List<TableSessionDTO> timeTableNowPlus2 = getTimeTableByDate(LocalDate.now().plusDays(2L));
        final List<TableSessionDTO> timeTableNowPlus3 = getTimeTableByDate(LocalDate.now().plusDays(3L));
        final List<TableSessionDTO> timeTableNowPlus4 = getTimeTableByDate(LocalDate.now().plusDays(4L));
        final List<TableSessionDTO> timeTableNowPlus5 = getTimeTableByDate(LocalDate.now().plusDays(5L));
        final List<TableSessionDTO> timeTableNowPlus6 = getTimeTableByDate(LocalDate.now().plusDays(6L));
        request.setAttribute(MOVIE_SES_LIST_TODAY, timeTableNow );
        request.setAttribute(MOVIE_SES_LIST_TOM, timeTableTomorrow );
        request.setAttribute(MOVIE_SES_LIST_NOW2, timeTableNowPlus2 );
        request.setAttribute(MOVIE_SES_LIST_NOW3, timeTableNowPlus3 );
        request.setAttribute(MOVIE_SES_LIST_NOW4, timeTableNowPlus4 );
        request.setAttribute(MOVIE_SES_LIST_NOW5, timeTableNowPlus5 );
        request.setAttribute(MOVIE_SES_LIST_NOW6, timeTableNowPlus6 );
        return Path.PAGE_SHOW_TIMETABLE;
    }


    private List<TableSessionDTO> getTimeTableByDate(LocalDate date){
        List<TableSessionDTO> view = new ArrayList<>();
        final List<MovieSession> sorted = movieService.findMovieSesByDate(date).
                stream().sorted(Comparator.comparing(MovieSession::getTime)).collect(Collectors.toList());
        if(sorted.size() == 0) {
            TableSessionDTO newDto = new TableSessionDTO();
            newDto.setMovieTitle("");
            newDto.setDate(date);
            view.add(newDto);
        }
        sorted.forEach(m-> {
            final Movie movieById = movieService.findMovieById(m.getMovieId());
            TableSessionDTO newDto = new TableSessionDTO();
            newDto.setMovieTitle(movieById.getTitle());
            newDto.setDate(m.getDate());
            newDto.setTime(m.getTime());
            view.add(newDto);
        });
        return view;
    }
}
