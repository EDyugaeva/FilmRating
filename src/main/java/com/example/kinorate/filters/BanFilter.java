package com.example.kinorate.filters;


import com.example.kinorate.model.User;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class BanFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest arg0,
                         ServletResponse arg1,
                         FilterChain arg2) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) arg0;


        log.info("Ban filter");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user.isBanned()) {
            log.info("Forwarding, user is banned");
            request.setAttribute("error", "You can not do this, because you are banned. Please, try again later");
            request.getRequestDispatcher("html/error.jsp").forward(request, arg1);
        }


        arg2.doFilter(arg0, arg1);
    }

    @Override
    public void destroy() {
    }
}
