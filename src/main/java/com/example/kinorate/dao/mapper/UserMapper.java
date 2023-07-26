package com.example.kinorate.dao.mapper;

import com.example.kinorate.model.*;
import lombok.extern.slf4j.Slf4j;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class UserMapper {

    public User getUser(ResultSet resultSet) {
        User user = null;
        log.info("Mapping rs to find a user");
        try {
            user = new User();
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


        } catch (SQLException e) {
            log.warn("Exception while mapping user rs");
            e.printStackTrace();
        }

        return user;
    }


    public User getFullUserDetails(ResultSet resultSet) throws SQLException {
        User user = new User();
        List<Comment> commentList = new ArrayList<>();
        List<Rate> rateList = new ArrayList<>();
        long userId = 0;

        while (resultSet.next()) {
            log.info("Mapping rs to find a user");
            if (userId == 0) {
                userId = resultSet.getLong("user_id");
                user.setId(userId);
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setBirthDate(resultSet.getDate("birth_date").toLocalDate());
                user.setRole(Role.valueOf(resultSet.getString("role")));
                user.setStatus(Integer.parseInt(resultSet.getString("status")));
                user.setBanned(resultSet.getBoolean("isBanned"));

            }

            if (userId != resultSet.getLong(1)) {
                break;
            }

            Comment comment = new Comment();
            Long commentId = resultSet.getLong("c_id");
            comment.setId(commentId);
            if (commentId != 0 && !commentList.contains(comment)) {

                comment.setFilm(resultSet.getLong("c_film_id"));
                comment.setAuthor(userId);
                comment.setText(resultSet.getString("comment"));
                Timestamp timestamp = resultSet.getTimestamp("date_time_of_creation");
                comment.setDate(timestamp.toLocalDateTime());

                commentList.add(comment);
            }

            Rate rate = new Rate();
            Long rateId = resultSet.getLong("r_id");
            rate.setId(rateId);
            if (rateId != 0 && !commentList.contains(rate)) {
                rate.setFilm(resultSet.getLong("r_film_id"));
                rate.setUser(userId);
                rate.setRate(resultSet.getInt("rate"));
                rateList.add(rate);
            }


        }

        user.setRatesList(rateList);
        user.setCommentsList(commentList);


        return user;
    }


}
