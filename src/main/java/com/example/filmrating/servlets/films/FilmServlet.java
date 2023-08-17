package com.example.filmrating.servlets.films;

import com.example.filmrating.model.Comment;
import com.example.filmrating.model.Film;
import com.example.filmrating.model.Rate;
import com.example.filmrating.model.User;
import com.example.filmrating.services.FilmService;
import com.example.filmrating.services.RateService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@WebServlet("/film")
@Slf4j
public class FilmServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long filmId = Integer.parseInt(request.getParameter("id"));
        log.info("Get info about film with id = {}", filmId);

        Film film = FilmService.findById(filmId).orElseThrow(() -> new NoSuchElementException("There is no films with that id"));

        List<Comment> commentList = film.getCommentsList();

        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            log.info("Find rate to film id = {} from user = {}", filmId, user.getId());
            Optional<Rate> rate = RateService.findRatesByUserIdAndFilmId(filmId, user.getId());
            if (rate.isPresent()) {
                request.setAttribute("rate", rate.get().getRate());
            }

        }

        request.setAttribute("film", film);
        request.setAttribute("comments", commentList);

        request.getRequestDispatcher("/html/film.jsp").forward(request, response);


    }
}
