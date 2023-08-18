package com.example.filmrating.services.impl;

import com.example.filmrating.dao.DaoFactory;
import com.example.filmrating.dao.RateDao;
import com.example.filmrating.model.Film;
import com.example.filmrating.model.Rate;
import com.example.filmrating.model.User;
import com.example.filmrating.services.FilmService;
import com.example.filmrating.services.RateService;
import com.example.filmrating.services.UserService;
import com.example.filmrating.utills.Calculating;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
public class RateServiceImpl implements RateService {

    private static final RateDao rateDao = DaoFactory.getInstance().getRateDao();
    private static final FilmService filmService = new FilmServiceImpl();
    private static final UserService userService = new UserServiceImpl();

    public List<Rate> findRatesByFilmId(long filmId) {
        return DaoFactory.getInstance().getRateDao().findRatesByFilmId(filmId);
    }


    public void setRating(Rate rate) {
        Long filmId = rate.getFilm();
        Long userId = rate.getUser();
        log.info("Set new rate to film with id = {}", filmId);

        Film film = filmService.findById(filmId).orElseThrow(()
                -> new NoSuchElementException(String.format("There is no films with that id %d", userId)));

        User user = userService.findById(userId).orElseThrow(()
                -> new NoSuchElementException(String.format("There is no users with that id %d", userId)));

        float grade = film.getRate();
        int status = user.getStatus();

        log.info("recalculating user status, previous was = {}", status);

        List<Rate> filmRatingList = findRatesByFilmId(film.getId());

        int newStatus = Calculating.calculateStatus(status, grade, rate.getRate(), filmRatingList.size());

        log.info("recalculating user status, new status is = {}", newStatus);
        user.setStatus(newStatus);

        log.info("recalculating film rating, before = {} ", grade);
        float newGrade = Calculating.calculateNewFilmRating(filmRatingList, rate);
        log.info("recalculating film rating, new = {} ", newGrade);
        film.setRate(newGrade);


        int rowAffectedInFilm = filmService.updateRating(film);

        if (rowAffectedInFilm != 1) {
            throw new RuntimeException("exception in setting new rating to film");
        }

        int rowAffectedInUsers = userService.update(user);
        if (rowAffectedInUsers != 1) {
            throw new RuntimeException("exception in setting new status to user");
        }

    }

    public Optional<Rate> findRatesByUserIdAndFilmId(long filmId, long userId) {
        return rateDao.findRatesByUserIdAndFilmId(filmId, userId);
    }

    public int save(Rate rate) {
        return rateDao.save(rate);

    }

    public int update(Rate rate) {
        return rateDao.update(rate);
    }
}
