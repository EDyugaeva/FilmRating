package com.example.kinorate.dao.mapper;

import com.example.kinorate.dao.CommentDao;
import com.example.kinorate.dao.RateDao;
import com.example.kinorate.model.Comment;
import com.example.kinorate.model.Rate;
import com.example.kinorate.model.Role;
import com.example.kinorate.model.User;
import lombok.extern.slf4j.Slf4j;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Slf4j
public class UserMapper {

    private final CommentDao commentDao = new CommentDao();
    private final RateDao rateDao = new RateDao();

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


            List<Comment> commentList = commentDao.findCommentsByUserId(id);
            List<Rate> rateList = rateDao.findRatesByUserId(id);

            user.setCommentsList(commentList);
            user.setRatesList(rateList);



        } catch (SQLException e) {
            log.warn("Exception while mapping user rs");
            e.printStackTrace();
        }

        return user;
    }


}
