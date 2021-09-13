package com.my.cinema.booking.controller.command.common;

import com.my.cinema.booking.controller.command.Command;
import com.my.cinema.booking.exceptions.EntityNotFoundException;
import com.my.cinema.booking.model.entity.User;
import com.my.cinema.booking.model.enums.Role;
import com.my.cinema.booking.service.UserService;
import com.my.cinema.booking.utils.LoginUserUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.my.cinema.booking.controller.command.Path.*;

public class EnterLoginCommand extends Command {
    private final Logger LOG = Logger.getLogger(EnterLoginCommand.class);
    private static final String EMAIL_PARAMETER = "email";
    private static final String PASSWORD_PARAMETER = "password";
    private static final String WRONG_DATA = "?wrongData=true";
    private UserService userService;

    public EnterLoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter(EMAIL_PARAMETER);
        String password = request.getParameter(PASSWORD_PARAMETER);
        User user;
        String contextAndServletPath = request.getContextPath() + request.getServletPath();
        LOG.info("check data email and password" + email + " " + password);
        if (inputWrongData(email, password)) {
            LOG.info("wrong data");
            return PAGE_LOGIN + WRONG_DATA;
        } else {
            user = LoginUserUtils.getLoginedUser(request.getSession());
            if (user != null) {
                LOG.info("logined person: " + user);
                if (Role.USER.getName().equals(user.getRole().getName())) {
                    LOG.info("return user account");
                    return REDIRECT + contextAndServletPath + USER_ACCOUNT;
                }
                if (Role.ADMIN.getName().equals(user.getRole().getName())) {
                    LOG.info("return admin account");
                    return REDIRECT + contextAndServletPath + ADMIN_ACCOUNT;
                }
            }
            try {
                user = userService.getUser(email, password);
            } catch (EntityNotFoundException e) {
                LOG.error("could not found user in 'EnterLoginCommand' " + e);
                return PAGE_ERROR_500;
            }

            LoginUserUtils.saveLoginedUser(request.getSession(), user);
            if (user.getRole().equals(Role.USER)) {
                LOG.info("return user account");
                return REDIRECT + contextAndServletPath + USER_ACCOUNT;
            }
            if (user.getRole().equals(Role.ADMIN)) {
                LOG.info("return admin account");
                return REDIRECT + contextAndServletPath + ADMIN_ACCOUNT;
            }

        }

       return REDIRECT + contextAndServletPath + LOGIN;

    }

    private boolean inputWrongData(String email, String password) {
        return !userService.checkUserExists(email, password);
    }
}
