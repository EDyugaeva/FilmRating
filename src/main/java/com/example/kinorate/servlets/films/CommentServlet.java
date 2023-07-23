package com.example.kinorate.servlets.films;

import com.example.kinorate.dao.CommentDao;
import com.example.kinorate.model.Comment;
import com.example.kinorate.model.User;
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

    CommentDao commentDao = new CommentDao();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        log.info("Send new comment to film");

        String textComment = req.getParameter("comment");
        Long film_id = Long.valueOf(req.getParameter("film_id"));
        HttpSession session = req.getSession();

        User user = (User) session.getAttribute("user");

        Comment comment = new Comment();
        comment.setFilm(film_id);
        comment.setAuthor(user.getId());
        comment.setText(textComment);

        System.out.println(comment);
        commentDao.addComment(comment);

        resp.sendRedirect("film?id=" + film_id);

    }
}


