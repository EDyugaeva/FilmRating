package com.example.kinorate.servlets;

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

@WebServlet(urlPatterns = "/top")
@Slf4j
public class Top extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Get 5 top films");

        FilmDao dao = new FilmDao();
        List<Film> topFilms = dao.findTop5Films();

        req.setAttribute("film1", topFilms.get(0));
        req.setAttribute("film2", topFilms.get(1));
        req.setAttribute("film3", topFilms.get(2));
        req.setAttribute("film4", topFilms.get(3));
        req.setAttribute("film5", topFilms.get(4));


    }
}
