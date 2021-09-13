package com.my.cinema.booking.controller.command.common;

import com.my.cinema.booking.controller.command.Command;
import com.my.cinema.booking.exceptions.EmailExistException;
import com.my.cinema.booking.exceptions.EntitySaveDaoException;
import com.my.cinema.booking.model.entity.User;
import com.my.cinema.booking.model.enums.Role;
import com.my.cinema.booking.service.UserService;
import com.my.cinema.booking.utils.Validator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.my.cinema.booking.controller.command.Path.*;

public class RegistrationCommand extends Command {
    private static final String NAME_PARAMETER = "name";
    private static final String EMAIL_PARAMETER = "email";
    private static final String PASSWORD_PARAMETER = "password";
    private static final String PASSWORD_REPEAT = "password_repeat";
    private static final String BAD_INPUT = "?badInput=true";
    private static final String BAD_EMAIL = "?badEmail=true";
    private static final String REG_AGAIN = "?successReg=true";
    private static final String REG_EXC = "?successReg=false";
    private final Logger LOG = Logger.getLogger(RegistrationCommand.class);
    private UserService userService;

    public RegistrationCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        final String name = request.getParameter(NAME_PARAMETER);
        final String email = request.getParameter(EMAIL_PARAMETER);
        final String password = request.getParameter(PASSWORD_PARAMETER);
        final String repeatPassword = request.getParameter(PASSWORD_REPEAT);

        if (name == null) {
            LOG.info("Name == null, return register user");
            return PAGE_REGISTER_USER;
        }
        if (Validator.notValidData(name, email, password, repeatPassword)) {
            LOG.info("wrong data input");
            return PAGE_REGISTER_USER + BAD_INPUT;
        }

        User user = new User.Builder().
                setName(name).
                setEmail(email).
                setRole(Role.USER).
                setPassword(password).build();
        String contextAndServletPath = request.getContextPath() + request.getServletPath();
        try {
            LOG.info("try write to database user");
            userService.saveUser(user);

        } catch (EmailExistException e) {
            LOG.error("EmailIsAlreadyTaken: ", e);
            return PAGE_REGISTER_USER + BAD_EMAIL;
        } catch (EntitySaveDaoException e) {
            LOG.error("Exception when save new user: " +  user);
            return REDIRECT + contextAndServletPath + LOGIN + REG_EXC;
        }

        LOG.info("return login page");
        return REDIRECT + contextAndServletPath + LOGIN + REG_AGAIN;

    }
}
