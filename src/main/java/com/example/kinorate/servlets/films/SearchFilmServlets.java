package com.example.kinorate.servlets.films;

import com.example.kinorate.dao.FilmDao;
import com.example.kinorate.model.Film;
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
    FilmDao filmDao = new FilmDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String searchString = req.getParameter("search");
        log.info("Searching film by {} ", searchString);


        List<Film> films = filmDao.searchFilmsByTitle(searchString);
        log.info("It was found {} films ", films.size());

        req.setAttribute("films", films);
        req.getRequestDispatcher("/html/searchfilms.jsp").include(req, resp);


    }
}
