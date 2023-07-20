package com.example.kinorate;

import com.example.kinorate.dao.FilmDao;
import com.example.kinorate.model.Film;
import com.example.kinorate.model.User;
import com.example.kinorate.utills.StatusCalculating;

public class RatingService {

    public static boolean setRating(long filmId, User user, int grade) {
        FilmDao filmDao = new FilmDao();
        Film film = filmDao.findFilmById(filmId);

        float rate = film.getRate();
        int status = user.getStatus();

        user.setStatus(StatusCalculating.calculateStatus(status, rate, grade));







        return false;
    }
}
