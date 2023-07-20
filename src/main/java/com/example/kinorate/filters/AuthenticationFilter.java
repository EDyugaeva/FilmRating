package com.example.kinorate.filters;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
public class AuthenticationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest arg0,
                         ServletResponse arg1,
                         FilterChain arg2) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) arg0;
        System.out.println("filter");

        System.out.println(request.getRequestURI());
        if ( request.getRequestURI().startsWith("/kinorate/mypage") ||
                request.getRequestURI().startsWith("/kinorate/filmCreating")) {
            System.out.println("if is here");

            HttpSession session = request.getSession();
            if (session.getAttribute("user") == null) {
                System.out.println("ready to forward");
                request.getRequestDispatcher("html/login.jsp").forward(request, arg1);
            }
        }

        arg2.doFilter(arg0, arg1);


    }

    @Override
    public void destroy() {
    }
}
