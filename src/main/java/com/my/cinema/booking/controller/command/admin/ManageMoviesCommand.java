package com.my.cinema.booking.controller.command.admin;

import com.my.cinema.booking.controller.command.Command;
import com.my.cinema.booking.controller.command.Path;
import com.my.cinema.booking.model.entity.Movie;
import com.my.cinema.booking.service.MovieService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ManageMoviesCommand extends Command {
    private final Logger LOG = Logger.getLogger(ManageMoviesCommand.class);
    private static final String ALL_MOVIE_LIST_ATTRIBUTE = "allMoviesList";
    private static final String PAGINATION_PARAMETER = "pagination";
    private static final String RECORD_PER_PAGE_ATTRIBUTE = "recordPerPage";
    private static final String PAGE_NUMBERS_ATTRIBUTE = "pageNumbers";

    private MovieService movieService;

    public ManageMoviesCommand(MovieService movieService) {
        this.movieService = movieService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.info("showing movies list for admin");
        int pageNumber;
        int recordPerPage = 3;
        int startIndex;
        int numberOfPages;
        String sPageNo = request.getParameter(PAGINATION_PARAMETER);
        LOG.info("get number of page " + sPageNo);
        pageNumber = getPageNumber(sPageNo);
        startIndex = (pageNumber * recordPerPage) - recordPerPage;
        int limitIndex = startIndex + recordPerPage;
        List<Movie> allMovies = movieService.findAllMovies();
        int totalNumberRecords = allMovies.size();
        if(limitIndex > totalNumberRecords){
            limitIndex = totalNumberRecords;
        }
        LOG.info("all movies list size: " + totalNumberRecords);
        List<Movie> moviesPages = allMovies.subList(startIndex, limitIndex);
        request.setAttribute(RECORD_PER_PAGE_ATTRIBUTE, recordPerPage);
        request.setAttribute(ALL_MOVIE_LIST_ATTRIBUTE, moviesPages);
        numberOfPages = totalNumberRecords / recordPerPage;
        if (totalNumberRecords > numberOfPages * recordPerPage) {
            numberOfPages = numberOfPages + 1;
        }
        LOG.info("set request numberOfPages " + numberOfPages);
        request.setAttribute(PAGE_NUMBERS_ATTRIBUTE, numberOfPages);
        return Path.PAGE_ADMIN_SHOW_MOVIES;
    }

    private int getPageNumber(String strNumber) {
        try {
            return Integer.parseInt(strNumber);
        } catch (IllegalArgumentException e) {
            LOG.error("caught exception in getPageNumber for pageNumber: " + strNumber);
        }
        return 1;
    }
}
