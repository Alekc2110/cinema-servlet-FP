package com.my.cinema.booking.controller.command.user;

import com.my.cinema.booking.controller.command.Command;
import com.my.cinema.booking.controller.command.Path;
import com.my.cinema.booking.model.entity.Movie;
import com.my.cinema.booking.service.MovieService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

public class ShowMoviesCommand extends Command {
    private final Logger LOG = Logger.getLogger(ShowMoviesCommand.class);
    private MovieService movieService;
    private static final String MOVIE_LIST_ATTRIBUTE = "movieList";
    private static final String PAGINATION_PARAMETER = "pagination";
    private static final String RECORD_PER_PAGE_ATTRIBUTE = "recordPerPage";
    private static final String PAGE_NUMBERS_ATTRIBUTE = "pageNumbers";

    public ShowMoviesCommand(MovieService movieService) {
        this.movieService = movieService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.info("show movie list in ShowMoviesCommand");
        int pageNumber;
        int recordPerPage = 3;
        int startIndex;
        int numberOfPages;
        String sPageNo = request.getParameter(PAGINATION_PARAMETER);
        LOG.info("get number of page " + sPageNo);
        pageNumber = getPageNumber(sPageNo);
        startIndex = (pageNumber * recordPerPage) - recordPerPage;
        int limitIndex = startIndex + recordPerPage;
        List<Movie> allActiveMovies = movieService.findAllMovies().stream().
                filter(movie -> movie.getMovieSessionList().size() > 0).collect(Collectors.toList());
        int totalNumberRecords = allActiveMovies.size();
        if(limitIndex > totalNumberRecords){
            limitIndex = totalNumberRecords;
        }
        LOG.info("all active movies list size: " + totalNumberRecords);
        List<Movie> moviesPages = allActiveMovies.subList(startIndex, limitIndex);
        request.setAttribute(RECORD_PER_PAGE_ATTRIBUTE, recordPerPage);
        request.setAttribute(MOVIE_LIST_ATTRIBUTE, moviesPages);
        numberOfPages = totalNumberRecords / recordPerPage;
        if (totalNumberRecords > numberOfPages * recordPerPage) {
            numberOfPages = numberOfPages + 1;
        }
        LOG.info("set request numberOfPages " + numberOfPages);
        request.setAttribute(PAGE_NUMBERS_ATTRIBUTE, numberOfPages);
        return Path.PAGE_SHOW_MOVIES_TABLE;
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
