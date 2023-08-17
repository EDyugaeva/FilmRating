package com.example.filmrating.dao.mapper;

import com.example.filmrating.model.*;
import com.example.filmrating.utills.Calculating;
import lombok.extern.slf4j.Slf4j;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class FilmMapper {

    public Film getFilm(ResultSet resultSet) throws SQLException {
        log.info("Mapping rs to find a film");

        Film film = new Film();
        List<Comment> commentList = new ArrayList<>();
        List<Rate> rateList = new ArrayList<>();

        film = new Film();
        final long filmId = getFilmId(resultSet, film);
        resultSet.previous();

        while (resultSet.next()) {
            if (filmId != resultSet.getLong(1)) {
                resultSet.previous();
                break;
            }

            Comment comment = new Comment();
            Long commentId = resultSet.getLong(6);
            comment.setId(commentId);
            if (commentId != 0 && !commentList.contains(comment)) {

                getComment(resultSet, comment, filmId);

                commentList.add(comment);
            }

            Rate rateObject = new Rate();
            Long rateId = resultSet.getLong(11);
            rateObject.setId(rateId);
            if (rateId != 0 && !rateList.contains(rateObject)) {
                getRate(resultSet, rateObject, filmId, rateList);
            }

        }

        film.setRatesList(rateList);
        film.setCommentsList(commentList);

        if (film.getRate() == 0) {
            film.setRate(Calculating.calculateFilmRating(rateList));
        }

        return film;
    }

    private static void getRate(ResultSet resultSet, Rate rateObject, long filmId, List<Rate> rateList) throws SQLException {
        rateObject.setFilm(filmId);
        rateObject.setUser(resultSet.getLong(12));
        rateObject.setRate(resultSet.getInt(13));
        rateList.add(rateObject);
    }

    private static void getComment(ResultSet resultSet, Comment comment, long filmId) throws SQLException {
        comment.setFilm(filmId);
        comment.setAuthor(resultSet.getLong(7));
        comment.setText(resultSet.getString(8));
        Timestamp timestamp = resultSet.getTimestamp(9);
        comment.setDate(timestamp.toLocalDateTime());
        comment.setAuthorName(resultSet.getString(10));
    }

    public long getFilmId(ResultSet resultSet, Film film) throws SQLException {
        long filmId = resultSet.getLong(1);
        film.setId(filmId);
        film.setTitle(resultSet.getString(2));
        film.setDescription(resultSet.getString(3));
        film.setImage(resultSet.getString(4));
        float rate = resultSet.getFloat(5);
        film.setRate(rate);
        return filmId;
    }


}
