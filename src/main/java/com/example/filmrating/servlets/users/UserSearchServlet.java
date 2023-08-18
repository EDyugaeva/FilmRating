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
import java.util.List;

@WebServlet("/user-search")
@Slf4j
public class UserSearchServlet extends HttpServlet {
    private static final UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Searching for user");
        String searchString = req.getParameter("search");

        List<User> users = userService.findUserByNameOrLastName(searchString);

        req.setAttribute("users", users);
        req.getRequestDispatcher("/html/usersearch.jsp").forward(req, resp);
    }
}
