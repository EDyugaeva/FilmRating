package com.example.kinorate.servlets;

import com.example.kinorate.RatingService;
import com.example.kinorate.dao.FilmDao;
import com.example.kinorate.dao.RateDao;
import com.example.kinorate.model.Film;
import com.example.kinorate.model.Rate;
import com.example.kinorate.model.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@WebServlet("/rate")
@Slf4j
public class RateServlet extends HttpServlet {
    RateDao rateDao = new RateDao();
    FilmDao filmDao = new FilmDao();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Send new rate to film");

        int grade = Integer.parseInt(req.getParameter("star"));
        HttpSession session = req.getSession();

        User user = (User) session.getAttribute("user");

        Long film_id = Long.valueOf(req.getParameter("film_id"));

        Film film = filmDao.findFilmById(film_id);
        Rate rate = new Rate();
        rate.setRate(grade);
        rate.setFilm(film);
        rate.setUser(user);

        int rowAffected = rateDao.createRate(rate);
        if (rowAffected != 1) {
            req.getRequestDispatcher("/html/error.jsp").include(req, resp);
            return;

        }
        System.out.println(rate);

        RatingService.setRating(rate);

        resp.sendRedirect("film?id="+film_id);


    }
}