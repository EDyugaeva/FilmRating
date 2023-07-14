package com.example.kinorate.servlets;

import com.example.kinorate.dao.UserDao;
import com.example.kinorate.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.swing.text.DateFormatter;
import java.io.IOException;
import java.io.Writer;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;

@WebServlet(name = "registerServlet", value = "/register")
public class RegisterServlet extends jakarta.servlet.http.HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        DateTimeFormatter formatter = new DateTimeFormatterBuilder().appendPattern("yyyy-MM-dd").toFormatter();
        String name = req.getParameter("name");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        LocalDate birthDate = LocalDate.parse(req.getParameter("birthDate"), formatter);

        System.out.println(name);
        System.out.println(lastName);

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
}
