package com.example.kinorate.servlets.users;

import com.example.kinorate.model.User;
import com.example.kinorate.services.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.NoSuchElementException;

@WebServlet("/ban")
@Slf4j
public class BanUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long userId = Long.valueOf(req.getParameter("id"));
        log.info("Change ban status to user with id = {}", userId);

        User user = UserService.findById(userId).orElseThrow(()
                -> new NoSuchElementException(String.format("There is no films with that id %d", userId)));

        int ban = Integer.parseInt(req.getParameter("ban"));
        if (ban == 1) {
            log.info("Ban user");
            user.setBanned(true);
        }
        else {
            log.info("Unban user");
            user.setBanned(false);
        }


        int rowAffected = UserService.update(user);

        if (rowAffected == 1) {
            resp.sendRedirect("user?id=" + userId);
        } else {
            log.warn("Error during updating user");
            req.getRequestDispatcher("/html/error.jsp").include(req, resp);

        }

    }

}
