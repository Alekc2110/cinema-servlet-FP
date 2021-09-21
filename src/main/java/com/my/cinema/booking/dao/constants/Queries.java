package com.my.cinema.booking.dao.constants;

public final class Queries {
    //UserDao
    public static final String GET_USER_BY_ID = "SELECT * FROM `user` u JOIN `user_role` u_r ON u.id = u_r.user_id JOIN `role` r ON u_r.role_id = r.id  WHERE u.id = ?";
    public static final String SAVE_USER = "INSERT INTO `user` (name, password, email)  VALUES (?,?,?)";
    public static final String GET_ROLE_ID = "SELECT id FROM `role` WHERE role_name = ?";
    public static final String SAVE_USER_ROLE = "INSERT INTO `user_role` (user_id, role_id)  VALUES (?,?)";
    public static final String GET_BY_EMAIL_PASSWORD = "SELECT * FROM `user` u JOIN `user_role` u_r ON u.id = u_r.user_id JOIN `role` r ON u_r.role_id = r.id WHERE u.email =? AND u.password = ?";


    //TicketDao
    public static final String SAVE_TICKET = "INSERT INTO `ticket` (movie_session_id, row_id, seat_id, order_id) VALUES (?,?,?,?)";
    public static final String GET_TICKETS = "SELECT * FROM `ticket` t JOIN `movie_session` ms ON t.movie_session_id = ms.id JOIN `row` r ON t.row_id = r.id JOIN `seat` s ON t.seat_id = s.id WHERE t.movie_session_id = ?";

//    //ShoppingCartDao
//    public static final String SAVE_SHOPPING_CART = "INSERT INTO `shopping_cart` (user_id) VALUE (?)";
//    public static final String GET_SHOPPING_CART = "SELECT * FROM `shopping_cart` where user_id = ?";

    //MovieDao
    public static final String GET_ALL_MOVIES = "SELECT * FROM `movie`";
    public static final String GET_M_S_BY_MOVIE_ID = "SELECT * FROM `movie_session` where movie_id = ?";
    public static final String GET_MOVIE_BY_ID = "SELECT * FROM `movie` where id = ?";
    public static final String DELETE_MOVIE_BY_ID = "DELETE FROM `movie` WHERE id = ?";
    public static final String UPDATE_MOVIE_BY_ID = "UPDATE `movie` SET title=?, description=?, photo_url=?, director=?, country=?, year=? WHERE id=?";
    public static final String SAVE_MOVIE = "INSERT INTO `movie` (title, description, photo_url, director, country, year) VALUES (?,?,?,?,?,?)";
    public static final String SAVE_MOVIE_SESSION = "INSERT INTO `movie_session` (movie_id, show_time, ticket_price) VALUES (?,?,?)";
    public static final String GET_MOVIE_SES_BY_ID = "SELECT * FROM `movie_session` where id = ?";
    public static final String UPDATE_MOVIE_SES_BY_ID = "UPDATE `movie_session` SET movie_id=?, show_time=?, ticket_price=? WHERE id=?";
    public static final String DELETE_MOVIE_SES_BY_ID = "DELETE FROM `movie_session` WHERE id = ?";

    //OrderDao
    public static final String GET_BOOKED_SEATS = "SELECT * FROM `movie_session_booked_seats` ms JOIN `seat` s ON ms.seat_id = s.id WHERE ms.movie_session_id = ?";
    public static final String GET_ALL_SEATS = "SELECT * FROM `seat`";
    public static final String GET_SEAT_BY_ID = "SELECT * FROM `seat` where id = ?";
    public static final String GET_ROW_BY_ID = "SELECT * FROM `row` where id = ?";
    public static final String SAVE_ORDER = "INSERT INTO `order` (order_time, user_id, order_price, status) VALUES (?,?,?,?)";
    public static final String UPDATE_ORDER_BY_ID = "UPDATE `order` SET order_time=?, user_id=?, order_price=?, status=? WHERE id=?";
    public static final String SAVE_BOOKED_SEATS = "INSERT INTO `movie_session_booked_seats` (movie_session_id, seat_id) VALUES (?,?)";
    public static final String DELETE_ORDER_BY_ID = "DELETE FROM `order` WHERE id = ?";
}
