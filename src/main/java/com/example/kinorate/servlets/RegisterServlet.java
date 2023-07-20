package com.example.kinorate.servlets;

import com.example.kinorate.dao.UserDao;
import com.example.kinorate.model.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Writer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

@WebServlet(name = "register", value = "/register")
public class RegisterServlet extends jakarta.servlet.http.HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String lastName = req.getParameter("last-name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String passwordRepeated = req.getParameter("password-repeat");
        LocalDate birthDate = LocalDate.parse(req.getParameter("birth-day"));

        if (!password.equals(passwordRepeated)) {
            System.out.println("Passwords don't match");
            RequestDispatcher dispatcher = req.getRequestDispatcher("html/error.jsp");
            dispatcher.include(req, resp);
        }

        User user = new User(name, lastName, email, password, birthDate);

        UserDao dao = new UserDao();

        Writer writer = resp.getWriter();

        int row = dao.registerUser(user);
        if (row == 1) {
            writer.write("User registered successfully");
        } else {
            writer.write("User registration failed");
        }


    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("RegisterServlet doGet");
        RequestDispatcher dispatcher = req.getRequestDispatcher("html/register.jsp");
        dispatcher.include(req, resp);

    }
}
