package com.example.bookstore.servlets;

import com.example.bookstore.models.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            User user = User.login(username, password);
            HttpSession session = request.getSession();
            session.setAttribute("session", username);
            System.out.println(session.getAttribute("session"));
            if (user != null) {
                request.setAttribute("user", user); // render_template(...) teki user=user olayı

                // aşağıdaki iki satır render_template e eşit
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/welcome.jsp");
                requestDispatcher.forward(request, response);
            } else {
                request.setAttribute("message", "invalid username or password"); // render_template(...) teki message=message olayı

                // aşağıdaki iki satır render_template e eşit
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/login.jsp");
                requestDispatcher.forward(request, response);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // aşağıdaki iki satır render_template e eşit
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/login.jsp");
        requestDispatcher.forward(request, response);
    }
}


