package com.my.cinema.booking.controller.command.user;

import com.my.cinema.booking.controller.command.Command;
import com.my.cinema.booking.controller.command.Path;
import com.my.cinema.booking.model.TableSessionDTO;
import com.my.cinema.booking.model.entity.Movie;
import com.my.cinema.booking.service.MovieService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ShowMoviesCommand extends Command {
    private final Logger LOG = Logger.getLogger(ShowMoviesCommand.class);
    private MovieService movieService;
    private static final String MOVIE_LIST_ATTRIBUTE = "movieList";
    private static final String PAGINATION_PARAMETER = "pagination";
    private static final String SORT_OPTION = "option";
    private static final String RECORD_PER_PAGE_ATTRIBUTE = "recordPerPage";
    private static final String PAGE_NUMBERS_ATTRIBUTE = "pageNumbers";
    private static final String SORT_BY_TITLE = "title";
    private static final String SORT_BY_DATE = "date";
    private static final String SORT_BY_TIME = "time";

    public ShowMoviesCommand(MovieService movieService) {
        this.movieService = movieService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.info("show movie list in ShowMoviesCommand");
        int pageNumber;
        int recordPerPage = 7;
        int startIndex;
        int numberOfPages;
        List<TableSessionDTO> viewList = new ArrayList<>();
        String searchOption = request.getParameter(SORT_OPTION);
        String sPageNo = request.getParameter(PAGINATION_PARAMETER);
        LOG.info("get number of page " + sPageNo);
        pageNumber = getPageNumber(sPageNo);
        startIndex = (pageNumber * recordPerPage) - recordPerPage;
        int limitIndex = startIndex + recordPerPage;
        List<Movie> allActiveMovies = movieService.findAllMovies().stream().
                filter(movie -> movie.getMovieSessionList().size() > 0).collect(Collectors.toList());
        allActiveMovies.forEach(movie ->
                movie.getMovieSessionList().forEach(ms -> {
                    viewList.add(new TableSessionDTO.Builder().setId(movie.getId()).
                            setMovieTitle(movie.getTitle()).setDate(ms.getDate()).setTime(ms.getTime()).build());
                }));
        if (searchOption != null && !searchOption.isEmpty()) {
            if (searchOption.equals(SORT_BY_TITLE)) {
                viewList.sort(Comparator.comparing(TableSessionDTO::getMovieTitle));
                request.setAttribute(SORT_OPTION, SORT_BY_TITLE);
            }
            if (searchOption.equals(SORT_BY_DATE)) {
                viewList.sort(Comparator.comparing(TableSessionDTO::getDate));
                request.setAttribute(SORT_OPTION, SORT_BY_DATE);
            }
            if (searchOption.equals(SORT_BY_TIME)) {
                viewList.sort(Comparator.comparing(TableSessionDTO::getTime));
                request.setAttribute(SORT_OPTION, SORT_BY_TIME);
            }
        }
        int totalNumberRecords = viewList.size();
        if (limitIndex > totalNumberRecords) {
            limitIndex = totalNumberRecords;
        }
        LOG.info("all active movies list size: " + totalNumberRecords);
        List<TableSessionDTO> moviesPages = viewList.subList(startIndex, limitIndex);
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
