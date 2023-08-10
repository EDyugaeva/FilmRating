package com.example.kinorate.services;

import com.example.kinorate.dao.DaoFactory;
import com.example.kinorate.model.Comment;

import java.util.Optional;

public class CommentService {

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
}
