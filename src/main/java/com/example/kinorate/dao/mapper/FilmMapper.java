package com.example.kinorate.dao.mapper;

import com.example.kinorate.model.*;
import com.example.kinorate.utills.Calculating;
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

        long filmId = 0;
        film = new Film();
        filmId = resultSet.getLong(1);
        film.setId(filmId);
        film.setTitle(resultSet.getString(2));
        film.setDescription(resultSet.getString(3));
        film.setImage(resultSet.getString(4));
        float rate = resultSet.getFloat(5);
        film.setRate(rate);

        while (resultSet.next()) {
            log.info("Mapping rs to find a film");

            if (filmId != resultSet.getLong(1)) {
                resultSet.previous();
                break;
            }

            Comment comment = new Comment();
            Long commentId = resultSet.getLong(6);
            comment.setId(commentId);
            if (commentId != 0 && !commentList.contains(comment)) {

                comment.setFilm(filmId);
                comment.setAuthor(resultSet.getLong(7));
                comment.setText(resultSet.getString(8));
                Timestamp timestamp = resultSet.getTimestamp(9);
                comment.setDate(timestamp.toLocalDateTime());
                comment.setAuthorName(resultSet.getString(10));

                commentList.add(comment);
            }

            Rate rateObject = new Rate();
            Long rateId = resultSet.getLong(11);
            rateObject.setId(rateId);
            if (rateId != 0 && !commentList.contains(rateObject)) {
                rateObject.setFilm(filmId);
                rateObject.setUser(resultSet.getLong(12));
                rateObject.setRate(resultSet.getInt(13));
                rateList.add(rateObject);
            }


        }

        film.setRatesList(rateList);
        film.setCommentsList(commentList);

        if (film.getRate() == 0) {
            film.setRate(Calculating.calculateFilmRating(rateList));
        }

        return film;
    }
    
    

}
