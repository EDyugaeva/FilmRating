package com.example.kinorate.servlets.films;

import com.example.kinorate.dao.impl.FilmDaoImpl;
import com.example.kinorate.model.Film;
import com.example.kinorate.services.FilmService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "search", value = "/search")
@Slf4j
public class SearchFilmServlets extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String searchString = req.getParameter("search");
        log.info("Searching film by {} ", searchString);


        List<Film> films = FilmService.findFilmByTitle(searchString);
        log.info("It was found {} films ", films.size());

        req.setAttribute("films", films);
        req.getRequestDispatcher("/html/searchfilms.jsp").include(req, resp);


    }
}
