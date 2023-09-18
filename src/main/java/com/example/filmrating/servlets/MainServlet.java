package com.example.filmrating.servlets;

import com.example.filmrating.services.FilmService;
import com.example.filmrating.services.UserService;
import com.example.filmrating.services.impl.FilmServiceImpl;
import com.example.filmrating.services.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class MainServlet extends HttpServlet {
    private static final UserService userService = new UserServiceImpl();
    private static final FilmService filmService = new FilmServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("MainServlet get method");

        req.setAttribute("topFilms", filmService.findTop5Films());
        req.setAttribute("topUsers", userService.findTop5Users());

        String requestURI = "/html/main.jsp";
        req.getRequestDispatcher(requestURI).forward(req, resp);
    }
}
