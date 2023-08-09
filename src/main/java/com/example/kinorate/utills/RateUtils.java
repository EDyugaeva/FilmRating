package com.example.kinorate.utills;

import com.example.kinorate.dao.FilmDao;
import com.example.kinorate.dao.RateDao;
import com.example.kinorate.dao.UserDao;
import com.example.kinorate.model.Film;
import com.example.kinorate.model.Rate;
import com.example.kinorate.model.User;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class RateUtils {


    public static void setRating(Rate rate) {
        FilmDao filmDao = new FilmDao();
        UserDao userDao = new UserDao();
        RateDao rateDao = new RateDao();


        Long film_id = rate.getFilm();
        Long user_id = rate.getUser();
        log.info("Set new rate to film with id = {}", film_id);

        Film film = filmDao.findFilmById(film_id);
        User user = userDao.findUserById(user_id);

        float grade = film.getRate();
        int status = user.getStatus();
        log.info("recalculating user status, previous was = {}", status);

        List<Rate> filmRatingList = rateDao.findRatesByFilmId(film.getId());

        int newStatus =Calculating.calculateStatus(status, grade, rate.getRate(), filmRatingList.size()) ;
        log.info("recalculating user status, new status is = {}", newStatus);
        user.setStatus(newStatus);


        log.info("recalculating film rating, before = {} ", grade);
        float newGrade = Calculating.calculateNewFilmRating(filmRatingList, rate);
        log.info("recalculating film rating, new = {} ", newGrade);
        film.setRate(newGrade);


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
