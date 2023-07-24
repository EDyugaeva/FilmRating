package com.example.kinorate.servlets.users;

import com.example.kinorate.dao.UserDao;
import com.example.kinorate.model.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@WebServlet("/login")
@Slf4j
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("html/login.jsp");
        dispatcher.include(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        log.info("Login with email = {}" ,email);

        HttpSession session = req.getSession();

        UserDao dao = new UserDao();
        boolean isValid = dao.validateUser(email, password);

        if (isValid) {
            log.info("User exist");
            User user = dao.loginUser(email, password);
            session.setAttribute("isAuthorised", true);
            session.setAttribute("user", user);
            session.setAttribute("role", user.getRole());

            log.warn("User role = {}", user.getRole());

        } else {
            log.warn("User doesn't exist");

            req.setAttribute("error", "Please check your credentials");
            req.getRequestDispatcher("html/error.jsp").include(req, resp);

        }
        req.getRequestDispatcher("html/login.jsp").include(req, resp);


    }
}
