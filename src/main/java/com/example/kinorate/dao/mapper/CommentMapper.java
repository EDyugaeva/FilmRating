package com.example.kinorate.dao.mapper;

import com.example.kinorate.model.Comment;
import lombok.extern.slf4j.Slf4j;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

@Slf4j
public class CommentMapper {

    public Comment getComment(ResultSet resultSet) {
        Comment comment = null;

        try {
            comment = new Comment();
            comment.setId(resultSet.getLong("id"));
            comment.setText(resultSet.getString("comment"));

            comment.setAuthor(resultSet.getLong("user_id"));

            comment.setFilm(resultSet.getLong("film_id"));
            comment.setAuthorName(resultSet.getString("author_name"));

            Timestamp timestamp = resultSet.getTimestamp("date_time_of_creation");
            comment.setDate(timestamp.toLocalDateTime());

        }
        catch (SQLException e) {
            log.warn("Exception while mapping comment rs");
            e.printStackTrace();
        }

        return comment;
    }
}
