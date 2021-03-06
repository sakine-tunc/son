package com.example.bookstore.servlets;

import com.example.bookstore.models.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/update")
public class UppdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String parameter = request.getParameter("id");
        int id = Integer.parseInt(parameter);

        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            System.out.println(username);
            System.out.println(password);

            if (username != null && !username.isEmpty() && password != null && !password.isEmpty()) {
                User.update(id, username, password);
                response.sendRedirect("list-users");
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String parameter = request.getParameter("id");
        int id = Integer.parseInt(parameter);

        try {
            User user = User.findById(id);
            request.setAttribute("user", user);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/update.jsp");
            requestDispatcher.forward(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}

