package com.my.cinema.booking.controller.command;

public interface Path {
    // jsp pages
    String PAGE_LOGIN = "/login.jsp";
    String PAGE_HOME = "/WEB-INF/home_page.jsp";
    String PAGE_FORBIDDEN_403 = "/WEB-INF/error/403.jsp";
    String PAGE_ERROR_500 = "/WEB-INF/error/500.jsp";
    String PAGE_USER_ACCOUNT = "/WEB-INF/user/userAccount.jsp";
    String PAGE_ADMIN_ACCOUNT = "/WEB-INF/admin/adminAccount.jsp";
    String PAGE_REGISTER_USER = "/WEB-INF/common/registerUser.jsp";
    String PAGE_SHOW_ALL_MOVIES = "/WEB-INF/common/showActiveMovies.jsp";
    String PAGE_MOVIE_INFO = "/WEB-INF/common/movieInfo.jsp";
    String PAGE_ADMIN_SHOW_MOVIES = "/WEB-INF/admin/showMovies.jsp";
    String PAGE_ADMIN_EDIT_MOVIE = "/WEB-INF/admin/editMovie.jsp";
    String PAGE_ADMIN_ADD_MOVIE = "/WEB-INF/admin/addMovie.jsp";
    String PAGE_ADMIN_ADD_MOVIE_S = "/WEB-INF/admin/addMovieSession.jsp";
    String PAGE_ADMIN_MOVIE_SESSION = "/WEB-INF/admin/movieSession.jsp";
    String PAGE_ADMIN_EDIT_MOVIE_S = "/WEB-INF/admin/editMovieSes.jsp";
    String PAGE_ORDER_MOVIE_S = "/WEB-INF/common/manageOrder.jsp";
    String PAGE_ORDER_TICKETS = "/WEB-INF/common/orderTickets.jsp";
    String PAGE_CONFIRM_ORDER = "/WEB-INF/user/confirmOrder.jsp";
    String PAGE_SHOW_MOVIES_TABLE = "/WEB-INF/user/showMoviesTable.jsp";
//    String PAGE__ERROR_PAGE = "/WEB-INF/error/errorPage.jsp";

    //commands
    String HOME = "/homePage";
    String ENTER_LOGIN = "/enterLogin";
    String REDIRECT = "redirect";
    String EMPTY_STR = "";
    String LOGIN = "/login";
    String LOGOUT = "/logOut";
    String REGISTER = "/register";
    String REGISTER_USER = "/registerUser";
    String USER_ACCOUNT = "/userAccount";
    String ADMIN_ACCOUNT = "/adminAccount";
    String SHOW_ALL_MOVIES = "/showMovies";
    String SHOW_MOVIE_DETAIL = "/movieDetail";
    String ADMIN_MANAGE_MOVIES = "/manageMovie";
    String ADMIN_MANAGE_MOVIE_SES = "/manageMovieSession";
    String ADMIN_EDIT_MOVIE = "/editMovie";
    String ADMIN_DELETE_MOVIE = "/deleteMovie";
    String ADMIN_ADD_MOVIE = "/addMovie";
    String ADMIN_EDIT_MOVIE_SESSION = "/editMovieSession";
    String ADMIN_DELETE_MOVIE_SESSION = "/deleteMovieSession";
    String ADMIN_ADD_MOVIE_SESSION = "/addMovieSession";
    String MANAGE_ORDER = "/manageOrder";
    String ORDER_TICKETS = "/orderTickets";
    String ADD_ORDER = "/addOrder";
    String CONFIRM_ORDER = "/confirmOrder";
    String CANCEL_ORDER = "/cancelOrder";
    String SHOW_MOVIE_TABLE = "/showMovieTable";
    String FORBIDDEN = "/forbidden";
}
