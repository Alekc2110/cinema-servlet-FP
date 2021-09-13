package com.my.cinema.booking.controller.command;

import com.my.cinema.booking.controller.command.admin.*;
import com.my.cinema.booking.controller.command.common.*;
import com.my.cinema.booking.controller.command.user.UserAccountCommand;
import com.my.cinema.booking.service.MovieService;
import com.my.cinema.booking.service.ShoppingCartService;
import com.my.cinema.booking.service.UserService;
import com.my.cinema.booking.utils.SimplePasswordEncoder;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

import static com.my.cinema.booking.controller.command.Path.*;

public class CommandContainer {
    private static final Logger LOG = Logger.getLogger(CommandContainer.class);

    private static Map<String, Command> commands = new HashMap<>();

    static {
        LOG.info("put all commands in HashMap");
        commands.put(HOME, new HomeCommand());
        commands.put(FORBIDDEN, new ErrorForbiddenCommand());
        commands.put(ADMIN_ACCOUNT, new AdminAccountCommand());
        commands.put(USER_ACCOUNT, new UserAccountCommand());
        commands.put(LOGIN, new LoginCommand());
        commands.put(LOGOUT, new LogOutCommand());
        commands.put(ENTER_LOGIN, new EnterLoginCommand(new UserService(new SimplePasswordEncoder(), new ShoppingCartService())));
        commands.put(REGISTER_USER, new RegisterUserCommand());
        commands.put(REGISTER, new RegistrationCommand(new UserService(new SimplePasswordEncoder(), new ShoppingCartService())));
        commands.put(SHOW_ALL_MOVIES, new ShowMoviesCommand(new MovieService()));
        commands.put(SHOW_MOVIE_DETAIL, new ShowMovieDetailCommand(new MovieService()));
        commands.put(ADMIN_MANAGE_MOVIES, new ManageMoviesCommand(new MovieService()));
        commands.put(ADMIN_MANAGE_MOVIE_SES, new ManageMovieSessionCommand(new MovieService()));
        commands.put(ADMIN_EDIT_MOVIE, new EditMovieCommand(new MovieService()));
        commands.put(ADMIN_DELETE_MOVIE, new DeleteMovieCommand(new MovieService()));
        commands.put(ADMIN_ADD_MOVIE, new AddMovieCommand(new MovieService()));
        commands.put(ADMIN_EDIT_MOVIE_SESSION, new EditMovieSessionCommand(new MovieService()));
        commands.put(ADMIN_DELETE_MOVIE_SESSION, new DeleteMovieSessionCommand(new MovieService()));
        commands.put(ADMIN_ADD_MOVIE_SESSION, new AddMovieSessionCommand(new MovieService()));

    }

    public static Command getCommand(String commandName) {
        return commands.get(commandName);
    }

}
