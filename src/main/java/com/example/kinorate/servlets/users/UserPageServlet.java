package com.example.kinorate.servlets.users;

import com.example.kinorate.dao.UserDao;
import com.example.kinorate.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@WebServlet("/user")
@Slf4j
public class UserPageServlet extends HttpServlet {
    UserDao userDao = new UserDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long userId = Long.parseLong(req.getParameter("id"));
        log.info("Get information about user with id = {}", userId);

        User user = userDao.findUserById(userId);

        req.setAttribute("user", user);
        req.getRequestDispatcher("html/userPublicPage.jsp").include(req, resp);

    }
}
