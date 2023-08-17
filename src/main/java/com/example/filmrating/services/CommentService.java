package com.example.filmrating.services;

import com.example.filmrating.model.Comment;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CommentService {

    Optional<Comment> findById(long id);
    //TODO rewrite with sending object
    int save(Comment comment);
    //TODO rewrite with sending object
    int update(Comment comment);
    void delete(long id);
    List<Comment> getCommentsByFilmId(long filmId);
    String getAuthorName(long id);
    Map<Comment, String> getCommentToFilmWithAuthorNameMap(long filmId);

}
