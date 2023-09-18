package com.example.filmrating.dao.mapper;

import com.example.filmrating.model.Film;
import com.example.filmrating.model.Rate;
import com.example.filmrating.utills.Calculating;
import lombok.extern.slf4j.Slf4j;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class FilmMapper {

    private static void getRate(ResultSet resultSet, Rate rateObject, long filmId, List<Rate> rateList) throws SQLException {
        rateObject.setFilm(filmId);
        rateObject.setUser(resultSet.getLong("r_user_id"));
        rateObject.setRate(resultSet.getInt("r_rate"));
        rateList.add(rateObject);
    }

    public Film getFilm(ResultSet resultSet) throws SQLException {
        log.info("Mapping rs to find a film");
        List<Rate> rateList = new ArrayList<>();
        Film film = getFilmFromRS(resultSet);
        final long filmId = film.getId();
        resultSet.previous();
        while (resultSet.next()) {
            if (filmId != resultSet.getLong("filmId")) {
                resultSet.previous();
                break;
            }
            Rate rateObject = new Rate();
            Long rateId = resultSet.getLong("rate_id");
            rateObject.setId(rateId);
            if (rateId != 0 && !rateList.contains(rateObject)) {
                getRate(resultSet, rateObject, filmId, rateList);
            }
        }
        film.setRatesList(rateList);
        if (film.getRate() == 0) {
            film.setRate(Calculating.calculateFilmRating(rateList));
        }
        return film;
    }

    public Film getFilmFromRS(ResultSet resultSet) throws SQLException {
        Film film = new Film();
        long filmId = resultSet.getLong("filmId");
        film.setId(filmId);
        film.setTitle(resultSet.getString("title"));
        film.setDescription(resultSet.getString("description"));
        film.setImage(resultSet.getString("image"));
        float rate = resultSet.getFloat("rate");
        film.setRate(rate);
        return film;
    }
}
