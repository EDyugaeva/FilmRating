package com.example.kinorate.dao;

import com.example.kinorate.model.Comment;
import com.example.kinorate.model.Film;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Slf4j
public class CommentDao {

    public int addComment(Comment comment) {
        log.info("Creating new comment");
        try {
            Connection connection = DBConnection.getConnectionToDataBase();

            String sql = "INSERT INTO comments (user_id, film_id, comment, date_time_of_creation) VALUES (?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, comment.getAuthor().getId());
            preparedStatement.setLong(2, comment.getFilm().getId());
            preparedStatement.setString(3, comment.getText());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));

            return preparedStatement.executeUpdate();


        } catch (SQLException e) {
            log.warn("SQL exception during saving new comment");
            throw new RuntimeException(e);
        }
    }

    public List<Comment> findCommentsByFilmId(Film film) {
        Comment comment = null;
        List<Comment> commentList = new ArrayList<>();

        log.info("Searching for comments to film with id = {}", film.getId());
        try {
            Connection connection = DBConnection.getConnectionToDataBase();

            String sql = "SELECT * from comments where film_id =? ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, film.getId());

            ResultSet resultSet = preparedStatement.executeQuery();
            UserDao userDao = new UserDao();

            while (resultSet.next()) {
                comment = new Comment();
                comment.setText(resultSet.getString("comment"));
                comment.setAuthor(userDao.findUserById(resultSet.getLong("user_id")));
                comment.setFilm(film);
                comment.setId(Long.valueOf(resultSet.getString("id")));

                Timestamp timestamp = resultSet.getTimestamp("date_time_of_creation");
                comment.setDate(timestamp.toLocalDateTime());

                commentList.add(comment);
            }
            return commentList;


        } catch (SQLException e) {
            log.warn("SQL exception during finding comments");
            throw new RuntimeException(e);
        }
    }
}
