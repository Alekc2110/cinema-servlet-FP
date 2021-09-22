package com.my.cinema.booking.utils.security;

import com.my.cinema.booking.model.enums.Role;

import java.util.*;

import static com.my.cinema.booking.controller.command.Path.*;

public class SecurityConfig {
    private static final Map<Role, List<String>> mapConfig = new HashMap<>();

    static {
        init();
    }

    private static void init() {
        List<String> urlUserPatterns = new ArrayList<>();
        urlUserPatterns.add(USER_ACCOUNT);
        urlUserPatterns.add(ADD_ORDER);
        urlUserPatterns.add(CONFIRM_ORDER);
        urlUserPatterns.add(CANCEL_ORDER);
        urlUserPatterns.add(SHOW_MOVIE_TABLE);
        urlUserPatterns.add(LOGOUT);

        mapConfig.put(Role.USER, urlUserPatterns);

        List<String> urlAdminPatterns = new ArrayList<>();
        urlAdminPatterns.add(ADMIN_ACCOUNT);
        urlAdminPatterns.add(ADMIN_MANAGE_MOVIES);
        urlAdminPatterns.add(ADMIN_MANAGE_MOVIE_SES);
        urlAdminPatterns.add(ADMIN_EDIT_MOVIE);
        urlAdminPatterns.add(ADMIN_DELETE_MOVIE);
        urlAdminPatterns.add(ADMIN_ADD_MOVIE);
        urlAdminPatterns.add(ADMIN_EDIT_MOVIE_SESSION);
        urlAdminPatterns.add(ADMIN_DELETE_MOVIE_SESSION);
        urlAdminPatterns.add(ADMIN_ADD_MOVIE_SESSION);
        urlAdminPatterns.add(ADD_ORDER);
        urlAdminPatterns.add(CONFIRM_ORDER);
        urlAdminPatterns.add(CANCEL_ORDER);
        urlAdminPatterns.add(SHOW_MOVIE_TABLE);
        urlAdminPatterns.add(LOGOUT);

        mapConfig.put(Role.ADMIN, urlAdminPatterns);
    }

    public static Set<Role> getAllAppRoles() {
        return mapConfig.keySet();
    }

    public static List<String> getUrlPatternsForRole(Role role) {
        return mapConfig.get(role);
    }
}
