package com.example.filmrating.filters;

import com.example.filmrating.model.Role;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class AdminFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) arg0;
        log.info("Admin filter");
        log.info("Checking credentials");
        HttpSession session = request.getSession();
        if (session.getAttribute("role") != Role.ADMIN) {
            log.info("Forwarding");
            request.setAttribute("error", "To access you need to be an admin");
            request.getRequestDispatcher("html/error.jsp").forward(request, arg1);
        }


        arg2.doFilter(arg0, arg1);

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
