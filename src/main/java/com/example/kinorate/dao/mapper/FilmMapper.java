package com.example.kinorate.dao.mapper;

import com.example.kinorate.dao.CommentDao;
import com.example.kinorate.dao.RateDao;
import com.example.kinorate.model.*;
import lombok.extern.slf4j.Slf4j;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Slf4j
public class FilmMapper {

    private final CommentDao commentDao = new CommentDao();
    private final RateDao rateDao = new RateDao();

    public Film getFilm(ResultSet resultSet) {
        Film film = null;
        log.info("Mapping rs to find a film");
        try {
            film = new Film();
            long id = resultSet.getLong("id");
            film.setId(id);
            film.setTitle(resultSet.getString("title"));
            film.setDescription(resultSet.getString("description"));
            film.setImage(resultSet.getString("image"));
            film.setRate(resultSet.getFloat("rate"));


            List<Comment> commentList = commentDao.findCommentsByFilmId(id);
            List<Rate> rateList = rateDao.findRatesByFilmId(id);
            if (commentList.size() != 0) {
                film.setCommentsList(commentList);
                System.out.println(commentList);
            }
            if (rateList.size() != 0) {
                film.setRatesList(rateList);
                System.out.println(rateList);

            }

            System.out.println(film);


        } catch (SQLException e) {
            log.warn("Exception while mapping film rs");
            e.printStackTrace();
        }

        return film;
    }
}
