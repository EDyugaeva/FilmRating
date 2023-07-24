package com.example.kinorate.servlets.films;

import com.example.kinorate.dao.CommentDao;
import com.example.kinorate.dao.FilmDao;
import com.example.kinorate.dao.RateDao;
import com.example.kinorate.model.Comment;
import com.example.kinorate.model.Film;
import com.example.kinorate.model.Rate;
import com.example.kinorate.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;

@WebServlet("/film")
@Slf4j
public class FilmServlet extends HttpServlet {
    FilmDao filmDao = new FilmDao();
    CommentDao commentDao = new CommentDao();
    RateDao rateDao = new RateDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long filmId = Integer.parseInt(request.getParameter("id"));
        log.info("Get info about film with id = {}", filmId);

        Film film = filmDao.findFilmById(filmId);


        List<Comment> commentList = commentDao.findCommentsByFilmId(filmId);

        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            log.info("Find rate to film id = {} from user = {}", filmId, user.getId());
            Rate rate = rateDao.findRatesByUserIdAndFilmId(filmId, user.getId());
            if (rate != null) {
                request.setAttribute("rate", rate.getRate());
            }

        }

        request.setAttribute("film", film);
        request.setAttribute("comments", commentList);

        request.getRequestDispatcher("/html/film.jsp").forward(request, response);


    }
}
