package com.example.kinorate.filters;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class AuthenticationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest arg0,
                         ServletResponse arg1,
                         FilterChain arg2) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) arg0;


        log.info("Authentification filter");
        if (request.getRequestURI().startsWith("/kinorate/mypage") ||
                request.getRequestURI().startsWith("/kinorate/filmCreating") ||
                request.getRequestURI().startsWith("kinorate/rate")
        ) {
            log.info("Checking credentials");
            HttpSession session = request.getSession();
            if (session.getAttribute("user") == null) {
                log.info("Forwarding");
                request.getRequestDispatcher("html/login.jsp").forward(request, arg1);
            }
        }

        arg2.doFilter(arg0, arg1);


    }

    @Override
    public void destroy() {
    }
}
