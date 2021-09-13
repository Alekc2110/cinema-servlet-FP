package com.my.cinema.booking.controller.filter;

import com.my.cinema.booking.model.entity.User;
import com.my.cinema.booking.model.enums.Role;
import com.my.cinema.booking.utils.LoginUserUtils;
import com.my.cinema.booking.utils.security.SecurityUtils;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.my.cinema.booking.controller.command.Path.*;


public class AuthenticationFilter implements Filter {
    private final Logger LOG = Logger.getLogger(AuthenticationFilter.class);

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String pathInfo = httpRequest.getPathInfo();
        String contextAndServletPath = httpRequest.getContextPath() + httpRequest.getServletPath();
        User loginedUser = LoginUserUtils.getLoginedUser(httpRequest.getSession());
        LOG.info("get all info in doFilter");
        if ((LOGIN.equals(pathInfo) || REGISTER.equals(pathInfo)) && loginedUser != null) {
            if (loginedUser.getRole().equals(Role.USER)) {
                LOG.info("role = USER, return user account");
                httpResponse.sendRedirect(contextAndServletPath + USER_ACCOUNT);
            }
            if (loginedUser.getRole().equals(Role.ADMIN)) {
                LOG.info("role = ADMIN, return admin account");
                httpResponse.sendRedirect(contextAndServletPath + ADMIN_ACCOUNT);
            }
            return;
        }
        LOG.info("check if page is secure");
        if (SecurityUtils.isSecurityPage(httpRequest)) {
            LOG.info("check if user has permission to access resource");
            if (loginedUser != null && SecurityUtils.hasPermission(httpRequest, loginedUser.getRole())) {
                LOG.info("person has access to this page");
                chain.doFilter(request, response);
            } else {
                    LOG.info("redirect to 403");
                    httpResponse.sendRedirect(contextAndServletPath + FORBIDDEN);
            }
        } else {
            LOG.info("page is not secure, doFilter");
            chain.doFilter(request, response);
        }
    }


    @Override
    public void destroy() {
    }
}