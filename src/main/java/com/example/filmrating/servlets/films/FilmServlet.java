package com.example.filmrating.servlets.films;

import com.example.filmrating.model.Comment;
import com.example.filmrating.model.Film;
import com.example.filmrating.model.Rate;
import com.example.filmrating.model.User;
import com.example.filmrating.services.CommentService;
import com.example.filmrating.services.FilmService;
import com.example.filmrating.services.RateService;
import com.example.filmrating.services.impl.CommentServiceImpl;
import com.example.filmrating.services.impl.FilmServiceImpl;
import com.example.filmrating.services.impl.RateServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@WebServlet("/film")
@Slf4j
public class FilmServlet extends HttpServlet {
    private static final FilmService filmService = new FilmServiceImpl();
    private static final RateService rateService = new RateServiceImpl();
    CommentService commentService = new CommentServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long filmId = Integer.parseInt(request.getParameter("id"));
        log.info("Get info about film with id = {}", filmId);

        Film film = filmService.findById(filmId).orElseThrow(
                () -> new NoSuchElementException("There is no films with that id"));
        Map<Comment, String> commentMap = commentService.getCommentToFilmWithAuthorNameMap(filmId);
        User user = (User) request.getSession().getAttribute("user");

        if (user != null) {
            log.info("Find rate to film id = {} from user = {}", filmId, user.getId());
            Optional<Rate> rate = rateService.findRatesByUserIdAndFilmId(filmId, user.getId());
            rate.ifPresent(value -> request.setAttribute("rate", value.getRate()));
        }

        request.setAttribute("film", film);
        request.setAttribute("comments", commentMap);

        request.getRequestDispatcher("/html/film.jsp").forward(request, response);
    }
}
