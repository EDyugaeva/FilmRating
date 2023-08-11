package com.example.kinorate.servlets;

import com.example.kinorate.services.FilmService;
import com.example.kinorate.services.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("MainServlet get method");

        req.setAttribute("topFilms", FilmService.findTop5Films());

        req.setAttribute("topUsers", UserService.findTop5Users());


        String requestURI = "/html/main.jsp";
        req.getRequestDispatcher(requestURI).forward(req, resp);
    }
}
