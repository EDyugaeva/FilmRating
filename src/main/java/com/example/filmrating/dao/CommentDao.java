package com.example.filmrating.dao;

import com.example.filmrating.model.Comment;

import java.util.List;

public interface CommentDao extends DaoInterface<Comment> {

    String getAuthorName(long id);

    List<Comment> findCommentsByFilmId(long filmId);

}
