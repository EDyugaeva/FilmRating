package com.example.kinorate;

import com.example.kinorate.dao.FilmDao;
import com.example.kinorate.dao.RateDao;
import com.example.kinorate.dao.UserDao;
import com.example.kinorate.model.Film;
import com.example.kinorate.model.Rate;
import com.example.kinorate.model.User;
import com.example.kinorate.utills.Calculating;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class RatingService {

    public static void setRating(Rate rate) {

        log.info("Set new rate to film");
        FilmDao filmDao = new FilmDao();
        UserDao userDao = new UserDao();
        RateDao rateDao = new RateDao();

        Film film = rate.getFilm();
        User user = rate.getUser();

        float grade = film.getRate();
        int status = user.getStatus();
        log.info("recalculating user status");

        user.setStatus(Calculating.calculateStatus(status, grade, rate.getRate()));


        log.info("recalculating film rating");
        List<Rate> filmRatingList = rateDao.findRatesByFilmId(film.getId());

        film.setRate(Calculating.calculateNewFilmRating(filmRatingList, rate));


        int rowAffectedInFilm = filmDao.updateRating(film);
        System.out.println("new rating = " + film.getRate());
        if (rowAffectedInFilm != 1) {
            throw new RuntimeException("exception in setting new rating to film");
        }

        int rowAffectedInUsers = userDao.updateUser(user);
        if (rowAffectedInUsers != 1) {
            throw new RuntimeException("exception in setting new status to user");
        }

    }
}
