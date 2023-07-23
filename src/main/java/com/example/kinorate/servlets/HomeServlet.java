package com.example.kinorate.servlets;

import com.example.kinorate.dao.FilmDao;
import com.example.kinorate.dao.UserDao;
import com.example.kinorate.model.Film;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "home", value = "/home")
@Slf4j
public class HomeServlet extends jakarta.servlet.http.HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        log.info("Homeservlet get method");

        FilmDao filmDao = new FilmDao();
        req.setAttribute("topFilms", filmDao.findTop5Films());

        UserDao userDao = new UserDao();
        req.setAttribute("topUsers", userDao.findTop5Users() );


        String requestURI = "/html/main.jsp";
        req.getRequestDispatcher(requestURI).forward(req, resp);
    }
}
