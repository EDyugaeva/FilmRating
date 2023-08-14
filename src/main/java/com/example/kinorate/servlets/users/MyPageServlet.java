package com.example.kinorate.servlets.users;

import com.example.kinorate.model.Role;
import com.example.kinorate.model.User;
import com.example.kinorate.services.UserService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@WebServlet("/mypage")
@Slf4j
public class MyPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Role role = (Role) session.getAttribute("role");
        log.warn("User role = {}", role);
        User user = (User) session.getAttribute("user");

        user = UserService.findById(user.getId()).get();
        session.setAttribute("user", user);

        log.info("Get info about user");
        RequestDispatcher dispatcher = req.getRequestDispatcher("html/mypage.jsp");
        dispatcher.include(req, resp);


    }
}
