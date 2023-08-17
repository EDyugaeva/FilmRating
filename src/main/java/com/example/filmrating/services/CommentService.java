package com.example.filmrating.services;

import com.example.filmrating.dao.CommentDao;
import com.example.filmrating.dao.DaoFactory;
import com.example.filmrating.model.Comment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CommentService {

    static CommentDao dao = DaoFactory.getInstance().getCommentDao();

    public static Optional<Comment> findById(long id) {
        return dao.findById(id);
    }

    public static int save(Comment comment) {
        return dao.save(comment);
    }

    public static int update(Comment comment)  {
        return dao.update(comment);
    }

    public static void delete(long id) {
        dao.delete(id);
    }

    public static List<Comment> getCommentsByFilmId(long filmId) {
        return dao.findCommentsByFilmId(filmId);
    }

    public static String getAuthorName(long id) {
        return dao.getAuthorName(id);
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
