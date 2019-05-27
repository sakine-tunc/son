package com.example.bookstore.servlets;

import com.example.bookstore.models.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/insert")
public class InsertServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            if (username != null && !username.isEmpty() && password != null && !password.isEmpty()) {
                User user = new User();
                user.insert(username, password);
                response.sendRedirect("list-users");
            } else {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/insert.jsp");
                requestDispatcher.forward(request, response);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // aşağıdaki iki satır render_template e eşit
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/insert.jsp");
        requestDispatcher.forward(request, response);
    }
}
