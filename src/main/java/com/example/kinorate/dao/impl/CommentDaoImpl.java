package com.example.kinorate.dao.impl;

import com.example.kinorate.dao.CommentDao;
import com.example.kinorate.dao.DBConnection;
import com.example.kinorate.dao.mapper.CommentMapper;
import com.example.kinorate.model.Comment;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
public class CommentDaoImpl implements CommentDao {
    Connection connection = DBConnection.getConnectionToDataBase();

    private final CommentMapper mapper = new CommentMapper();
    private static final String INSERT = "INSERT INTO comments (user_id, film_id, comment, date_time_of_creation, author_name) VALUES (?, ?, ?, ?, ?)";
    private static final String FIND_BY_ID = "SELECT * from comments where id =?";
    private static final String FIND_ALL = "SELECT * from comments";
    private static final String UPDATE = "UPDATE comments SET user_id = ?, film_id = ?, comment = ?, " +
            "date_time_of_creation = ?, author_name = ? where id = ?";
    private static final String DELETE_BY_ID = "DELETE  from comments WHERE id = ?";

    @Override
    public int save(Comment comment) {
        log.info("Creating new comment");
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT);) {

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

    @Override
    public Optional<Comment> findById(long id) {
        log.info("searching comment by id {}", id);
        Comment comment = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {

            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                comment = mapper.getComment(rs);
            }

        } catch (SQLException e) {
            log.warn("SQL exception while finding comment by id");
            e.printStackTrace();
        }

        return Optional.ofNullable(comment);
    }

    @Override
    public List<Comment> getAll() {
        log.info("searching for all comments");
        List<Comment> comments = new ArrayList<>();
        Comment comment = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL)) {

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                comment = mapper.getComment(rs);
                comments.add(comment);
            }

        } catch (SQLException e) {
            log.warn("SQL exception while comments");
            e.printStackTrace();
        }

        return comments;
    }

    @Override
    public int update(Comment obj) {
        log.info("Updating comment with id = {}", obj.getId());

        int rowsAffected = 0;

        try (PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            statement.setLong(1, obj.getAuthor());
            statement.setLong(2, obj.getFilm());
            statement.setString(3, obj.getText());
            statement.setTimestamp(4, Timestamp.valueOf(obj.getDate()));
            statement.setString(5, obj.getAuthorName());
            statement.setLong(6, obj.getId());

            rowsAffected = statement.executeUpdate();

        } catch (SQLException e) {
            log.warn("SQL exception while updating comment {}", obj.getId());
            e.printStackTrace();
        }
        return rowsAffected;


    }

    @Override
    public void delete(long id) {
        log.info("Deleting comment by id = {}", id);
        try (PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID)) {
            statement.setLong(1, id);
            statement.execute();
        } catch (SQLException e) {
            log.warn("SQL exception while deleting comment with id =  {}", id);
            e.printStackTrace();
        }


    }

}
