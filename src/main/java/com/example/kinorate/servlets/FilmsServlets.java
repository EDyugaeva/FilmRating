package com.example.kinorate.servlets;

import com.example.kinorate.dao.FilmDao;
import com.example.kinorate.model.Film;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "filmsServlets", value = "/films-servlets")
public class FilmsServlets extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        System.out.println("doGet");

        String searchString = req.getParameter("search");
        System.out.println("search: " + searchString);

        FilmDao filmDao = new FilmDao();

        List<Film> films = filmDao.searchFilms(searchString);
        System.out.println(films.size());

        PrintWriter out = resp.getWriter();

        for (Film film : films) {
            out.write(film.toString());
            out.write("<br/>");

            System.out.println(film.toString());

        }





    }
}
