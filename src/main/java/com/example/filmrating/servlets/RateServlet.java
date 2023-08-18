package com.example.filmrating.servlets;

import com.example.filmrating.services.RateService;
import com.example.filmrating.services.impl.RateServiceImpl;
import com.example.filmrating.model.Rate;
import com.example.filmrating.model.User;
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Send new rate to film");

        int grade = Integer.parseInt(req.getParameter("star"));
        HttpSession session = req.getSession();

        User user = (User) session.getAttribute("user");

        long filmId = Long.parseLong(req.getParameter("film_id"));

        Rate rate = RateServiceImpl.findRatesByUserIdAndFilmId(filmId, user.getId()).orElse(new Rate());

        int rowAffected = 0;
        if (rate.getFilm() == null) {
            log.info("Creating new rate");
            rate = new Rate();
            rate.setRate(grade);
            rate.setFilm(filmId);
            rate.setUser(user.getId());

            rowAffected = RateServiceImpl.save(rate);

        } else {
            log.info("Rewriting old rate");
            rate.setRate(grade);
            rowAffected = RateServiceImpl.update(rate);
            req.setAttribute("rewriting", true);

        }


        if (rowAffected != 1) {
            req.setAttribute("error", "Error while saving data to database");
            req.getRequestDispatcher("/html/error.jsp").include(req, resp);
            return;

        }
        log.info("New rate to film = {} is {}", filmId, rate);

        RateServiceImpl.setRating(rate);
        resp.sendRedirect("film?id="+filmId);


    }
}