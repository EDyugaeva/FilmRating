package com.example.kinorate.dao;

import com.example.kinorate.dao.mapper.CommentMapper;
import com.example.kinorate.model.Comment;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Slf4j
public class CommentDao {

    private final CommentMapper mapper = new CommentMapper();
    private static final String INSERT = "INSERT INTO comments (user_id, film_id, comment, date_time_of_creation, author_name) VALUES (?, ?, ?, ?, ?)";
    private static final String FIND_BY_FILM_ID = "SELECT * from comments where film_id =?";
    private static final String FIND_BY_USER_ID = "SELECT * from comments where user_id =?";

    public int addComment(Comment comment) {
        log.info("Creating new comment");
        try {
            Connection connection = DBConnection.getConnectionToDataBase();

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setLong(1, comment.getAuthor());
            preparedStatement.setLong(2, comment.getFilm());
            preparedStatement.setString(3, comment.getText());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            preparedStatement.setString(5, comment.getAuthorName());

            return preparedStatement.executeUpdate();


        } catch (SQLException e) {
            log.warn("SQL exception during saving new comment");
            throw new RuntimeException(e);
        }
    }

    public List<Comment> findCommentsByFilmId(Long filmId) {
        Comment comment = null;
        List<Comment> commentList = new ArrayList<>();

        log.info("Searching for comments to film with id = {}", filmId);
        try {
            Connection connection = DBConnection.getConnectionToDataBase();

            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_FILM_ID);

            preparedStatement.setLong(1, filmId);

            ResultSet resultSet = preparedStatement.executeQuery();
            UserDao userDao = new UserDao();

            while (resultSet.next()) {
                comment = mapper.getComment(resultSet);
                commentList.add(comment);
            }
            return commentList;

        } catch (SQLException e) {
            log.warn("SQL exception during finding comments");
            throw new RuntimeException(e);
        }
    }

    public List<Comment> findCommentsByUserId(Long userId) {
        Comment comment = null;
        List<Comment> commentList = new ArrayList<>();

        log.info("Searching for comments to user with id = {}", userId);
        try {
            Connection connection = DBConnection.getConnectionToDataBase();

            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_FILM_ID);

            preparedStatement.setLong(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();
            UserDao userDao = new UserDao();

            while (resultSet.next()) {
                comment = mapper.getComment(resultSet);
                commentList.add(comment);
            }
            return commentList;

        } catch (SQLException e) {
            log.warn("SQL exception during finding comments");
            throw new RuntimeException(e);
        }
    }
}
