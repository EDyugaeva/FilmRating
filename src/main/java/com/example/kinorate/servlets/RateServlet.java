package com.example.kinorate.servlets;

import com.example.kinorate.utills.RateUtils;
import com.example.kinorate.dao.RateDao;
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
    RateDao rateDao = new RateDao();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Send new rate to film");

        int grade = Integer.parseInt(req.getParameter("star"));
        HttpSession session = req.getSession();

        User user = (User) session.getAttribute("user");

        long film_id = Long.parseLong(req.getParameter("film_id"));

        Rate rate = rateDao.findRatesByUserIdAndFilmId(film_id, user.getId());
        int rowAffected = 0;
        if (rate == null) {
            log.info("Creating new rate");
            rate = new Rate();
            rate.setRate(grade);
            rate.setFilm(film_id);
            rate.setUser(user.getId());

            rowAffected = rateDao.createRate(rate);

        } else {
            log.info("Rewriting old rate");
            rate.setRate(grade);
            rowAffected = rateDao.updateRate(rate);
            req.setAttribute("rewriting", true);

        }


        if (rowAffected != 1) {
            req.setAttribute("error", "Error while saving data to database");
            req.getRequestDispatcher("/html/error.jsp").include(req, resp);
            return;

        }
        log.info("New rate to film = {} is {}", film_id, rate);

        RateUtils.setRating(rate);
        resp.sendRedirect("film?id="+film_id);


    }
}
