package com.example.kinorate.servlets;

import com.example.kinorate.dao.CommentDao;
import com.example.kinorate.dao.FilmDao;
import com.example.kinorate.model.Comment;
import com.example.kinorate.model.Film;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/film")
public class FilmServlet extends HttpServlet {
    FilmDao filmDao = new FilmDao();
    CommentDao commentDao = new CommentDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the product ID from the request parameter

        long filmId = Integer.parseInt(request.getParameter("id"));

        // Retrieve the specific film based on the ID
        Film film = filmDao.findFilmById(filmId);


        List<Comment> commentList = commentDao.findCommentsByFilmId(film);
        for (Comment comment:
             commentList) {
            System.out.println(comment);

        }

        // Set the film object as an attribute in the request
        request.setAttribute("film", film);
        request.setAttribute("comments", commentList);

        // Forward the request to the JSP page for rendering
        request.getRequestDispatcher("/html/film.jsp").forward(request, response);


    }
}
