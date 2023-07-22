package com.example.kinorate.servlets;

import com.example.kinorate.dao.CommentDao;
import com.example.kinorate.dao.FilmDao;
import com.example.kinorate.model.Comment;
import com.example.kinorate.model.Film;
import com.example.kinorate.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@WebServlet("/comment")
@Slf4j
public class CommentServlet extends HttpServlet {

    FilmDao filmDao = new FilmDao();
    CommentDao commentDao = new CommentDao();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Send new comment to film");

        String textComment = req.getParameter("comment");
        Long film_id = Long.valueOf(req.getParameter("film_id"));
        HttpSession session = req.getSession();

        Film film = filmDao.findFilmById(film_id);
        User user = new User();
        user.setName("name setted");
        user.setId(7L);
        if (session.getAttribute("user") != null) {

           user = (User) session.getAttribute("user");
        }

        Comment comment = new Comment();
        comment.setFilm(film);
        comment.setAuthor(user);
        comment.setText(textComment);

        System.out.println(comment);
        commentDao.addComment(comment);

        resp.sendRedirect("film?id="+film_id);

    }
}


