package com.my.cinema.booking.utils.security;

import com.my.cinema.booking.model.enums.Role;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

public class SecurityUtils {
    public static boolean isSecurityPage(HttpServletRequest request) {
        Set<Role> roles = SecurityConfig.getAllAppRoles();
        for (Role role : roles) {
            List<String> urlPatterns = SecurityConfig.getUrlPatternsForRole(role);
            if (urlPatterns != null && urlPatterns.contains(request.getPathInfo())) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasPermission(HttpServletRequest request, Role userRole) {
        return SecurityConfig.getUrlPatternsForRole(userRole).contains(request.getPathInfo());
    }
}
