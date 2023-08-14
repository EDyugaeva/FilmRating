package com.example.kinorate.servlets;

import com.example.kinorate.services.RateService;
import com.example.kinorate.model.Rate;
import com.example.kinorate.model.User;
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

        Rate rate = RateService.findRatesByUserIdAndFilmId(filmId, user.getId()).orElse(new Rate());

        int rowAffected = 0;
        if (rate.getFilm() == null) {
            log.info("Creating new rate");
            rate = new Rate();
            rate.setRate(grade);
            rate.setFilm(filmId);
            rate.setUser(user.getId());

            rowAffected = RateService.save(rate);

        } else {
            log.info("Rewriting old rate");
            rate.setRate(grade);
            rowAffected = RateService.update(rate);
            req.setAttribute("rewriting", true);

        }


        if (rowAffected != 1) {
            req.setAttribute("error", "Error while saving data to database");
            req.getRequestDispatcher("/html/error.jsp").include(req, resp);
            return;

        }
        log.info("New rate to film = {} is {}", filmId, rate);

        RateService.setRating(rate);
        resp.sendRedirect("film?id="+filmId);


    }
}
