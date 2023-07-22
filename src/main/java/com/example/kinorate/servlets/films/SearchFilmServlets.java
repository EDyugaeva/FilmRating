package com.example.kinorate.servlets.films;

import com.example.kinorate.dao.FilmDao;
import com.example.kinorate.model.Film;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "search", value = "/search")
public class SearchFilmServlets extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String searchString = req.getParameter("search");

        System.out.println(searchString);

        FilmDao filmDao = new FilmDao();

        List<Film> films = filmDao.searchFilmsByTitle(searchString);

        req.setAttribute("films", films);
        req.getRequestDispatcher("/html/searchfilms.jsp").forward(req, resp);


    }
}
