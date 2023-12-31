package com.example.filmrating.services.impl;

import com.example.filmrating.dao.CommentDao;
import com.example.filmrating.dao.DaoFactory;
import com.example.filmrating.model.Comment;
import com.example.filmrating.services.CommentService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CommentServiceImpl implements CommentService {
    private static final CommentDao commentDao = DaoFactory.getInstance().getCommentDao();


    public  Optional<Comment> findById(long id) {
        return commentDao.findById(id);
    }

    public  int save(Comment comment) {
        return commentDao.save(comment);
    }

    public  int update(Comment comment)  {
        return commentDao.update(comment);
    }

    public  void delete(long id) {
        commentDao.delete(id);
    }

    public  List<Comment> getCommentsByFilmId(long filmId) {
        return commentDao.findCommentsByFilmId(filmId);
    }

    public  String getAuthorName(long id) {
        return commentDao.getAuthorName(id);
    }

    public  Map<Comment, String> getCommentToFilmWithAuthorNameMap(long filmId) {
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
