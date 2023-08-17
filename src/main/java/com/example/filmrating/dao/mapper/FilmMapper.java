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
            if (filmId != resultSet.getLong("filmId")) {
                resultSet.previous();
                break;
            }

            Comment comment = new Comment();
            Long commentId = resultSet.getLong("comment_id");
            comment.setId(commentId);
            if (commentId != 0 && !commentList.contains(comment)) {
                getComment(resultSet, comment, filmId);
                commentList.add(comment);
            }

            Rate rateObject = new Rate();
            Long rateId = resultSet.getLong("rate_id");
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
        rateObject.setUser(resultSet.getLong("r_user_id"));
        rateObject.setRate(resultSet.getInt("r_rate"));
        rateList.add(rateObject);
    }

    private static void getComment(ResultSet resultSet, Comment comment, long filmId) throws SQLException {
        comment.setFilm(filmId);
        comment.setAuthor(resultSet.getLong("comment_user_id"));
        comment.setText(resultSet.getString("comment"));
        Timestamp timestamp = resultSet.getTimestamp("date_time_of_creation");
        comment.setDate(timestamp.toLocalDateTime());
    }

    public long getFilmId(ResultSet resultSet, Film film) throws SQLException {
        long filmId = resultSet.getLong("filmId");
        film.setId(filmId);
        film.setTitle(resultSet.getString("title"));
        film.setDescription(resultSet.getString("description"));
        film.setImage(resultSet.getString("image"));
        float rate = resultSet.getFloat("rate");
        film.setRate(rate);
        return filmId;
    }


}
