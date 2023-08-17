package com.example.filmrating.dao.mapper;

import com.example.filmrating.model.*;
import lombok.extern.slf4j.Slf4j;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class UserMapper {

    public User getUserDetails(ResultSet resultSet) {
        User user = null;
        log.info("Mapping rs to find a user");
        try {
            user = new User();
            long id = getUser(resultSet, user);
            log.info("User with id = {} is found ", id);


        } catch (SQLException e) {
            log.warn("Exception while mapping user rs");
            e.printStackTrace();
        }

        return user;
    }

    private long getUser(ResultSet resultSet, User user) throws SQLException {
        long id = resultSet.getLong("id");
        user.setId(id);
        user.setName(resultSet.getString("name"));
        user.setLastName(resultSet.getString("last_name"));
        user.setEmail(resultSet.getString("email"));
        user.setPassword(resultSet.getString("password"));
        user.setBirthDate(resultSet.getDate("birth_date").toLocalDate());
        user.setRole(Role.valueOf(resultSet.getString("role")));
        user.setStatus(Integer.parseInt(resultSet.getString("status")));
        user.setBanned(resultSet.getBoolean("isBanned"));
        return id;
    }


    public User getFullUserDetails(ResultSet resultSet) throws SQLException {
        User user = new User();
        List<Comment> commentList = new ArrayList<>();
        List<Rate> rateList = new ArrayList<>();
        long userId = getUser(resultSet, user);

        while (resultSet.next()) {
            log.info("Mapping rs to find a user");

            Comment comment = new Comment();
            Long commentId = resultSet.getLong("c_id");
            if (commentId != 0 && !commentList.contains(comment)) {
                getComment(resultSet, comment, userId, commentId);
                commentList.add(comment);
            }

            Rate rate = new Rate();
            Long rateId = resultSet.getLong("r_id");
            if (rateId != 0 && !commentList.contains(rate)) {
                getRate(resultSet, rate, userId, rateId);
                rateList.add(rate);
            }

        }

        user.setRatesList(rateList);
        user.setCommentsList(commentList);


        return user;
    }

    private static void getRate(ResultSet resultSet, Rate rate,
                                long userId, long rateId) throws SQLException {
        rate.setId(rateId);
        rate.setFilm(resultSet.getLong("r_film_id"));
        rate.setUser(userId);
        rate.setRate(resultSet.getInt("rate"));
    }

    private static void getComment(ResultSet resultSet, Comment comment,
                                   long userId, long commentId) throws SQLException {
        comment.setId(commentId);
        comment.setFilm(resultSet.getLong("c_film_id"));
        comment.setAuthor(userId);
        comment.setText(resultSet.getString("comment"));
        Timestamp timestamp = resultSet.getTimestamp("date_time_of_creation");
        comment.setDate(timestamp.toLocalDateTime());
    }


}
