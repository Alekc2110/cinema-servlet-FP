package com.my.cinema.booking.utils;

import com.my.cinema.booking.model.entity.User;

import javax.servlet.http.HttpSession;

public class LoginUserUtils {
    private static final String SESSION_ATTRIBUTE = "loginedUser";

    public static void saveLoginedUser(HttpSession session, User loginedUser) {
        session.setAttribute(SESSION_ATTRIBUTE, loginedUser);
    }

    public static User getLoginedUser(HttpSession session) {
        return (User) session.getAttribute(SESSION_ATTRIBUTE);
    }
}
