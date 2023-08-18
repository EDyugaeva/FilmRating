package com.example.filmrating.servlets.users;

import com.example.filmrating.model.User;
import com.example.filmrating.services.UserService;
import com.example.filmrating.services.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.NoSuchElementException;

@WebServlet("/user")
@Slf4j
public class UserPageServlet extends HttpServlet {
    private static final UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long userId = Long.parseLong(req.getParameter("id"));
        log.info("Get information about user with id = {}", userId);

        User user = userService.findById(userId).orElseThrow(()
                -> new NoSuchElementException(String.format("There is no films with that id %d", userId)));

        req.setAttribute("user", user);
        req.getRequestDispatcher("html/userPublicPage.jsp").include(req, resp);

    }
}
