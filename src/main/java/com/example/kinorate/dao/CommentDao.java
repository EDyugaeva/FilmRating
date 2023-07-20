package com.example.kinorate.dao;

import com.example.kinorate.model.Comment;

import java.sql.*;
import java.time.LocalDateTime;

public class CommentDao {

    public int addComment(Comment comment) {
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
            throw new RuntimeException(e);
        }
    }
}
