package com.example.kinorate.servlets.films;

import com.example.kinorate.dao.FilmDao;
import com.example.kinorate.model.Film;
import com.example.kinorate.utills.FileUtills;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@WebServlet("/film-deleting")
@Slf4j
public class FilmDeletingServlet extends HttpServlet {
    FilmDao dao = new FilmDao();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long filmId = Long.valueOf(req.getParameter("film_id"));
        log.info("Delete film with id = {}", filmId);
        Film film = dao.findFilmById(filmId);
        dao.deleteFilmById(filmId);
        FileUtills.deleteFile(film.getImage());
        req.setAttribute("info", "film was deleted");
        req.getRequestDispatcher("html/OK.jsp").include(req, resp);
        req.getRequestDispatcher("/mypage").include(req, resp);


    }
}
