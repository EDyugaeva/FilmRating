package com.example.filmrating.services.impl;

import com.example.filmrating.dao.CommentDao;
import com.example.filmrating.dao.DaoFactory;
import com.example.filmrating.model.Comment;
import com.example.filmrating.services.CommentService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CommentServiceImpl  {


    public static Optional<Comment> findById(long id) {
        return DaoFactory.getInstance().getCommentDao().findById(id);
    }

    public static int save(Comment comment) {
        return DaoFactory.getInstance().getCommentDao().save(comment);
    }

    public static int update(Comment comment)  {
        return DaoFactory.getInstance().getCommentDao().update(comment);
    }

    public static void delete(long id) {
        DaoFactory.getInstance().getCommentDao().delete(id);
    }

    public static List<Comment> getCommentsByFilmId(long filmId) {
        return DaoFactory.getInstance().getCommentDao().findCommentsByFilmId(filmId);
    }

    public static String getAuthorName(long id) {
        return DaoFactory.getInstance().getCommentDao().getAuthorName(id);
    }

    public static Map<Comment, String> getCommentToFilmWithAuthorNameMap(long filmId) {
        Map<Comment, String> commentStringMap = new HashMap<>();
        List<Comment> comments = getCommentsByFilmId(filmId);
        for (Comment comment:
             comments) {
            String author = getAuthorName(comment.getId());
            commentStringMap.put(comment, author);

        }
        return commentStringMap;
    }
}
