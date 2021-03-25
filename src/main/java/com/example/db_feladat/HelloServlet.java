package com.example.db_feladat;

import java.io.*;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "Request Servlet", value = "/log")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Driver driver = DriverManager.getDriver("com.mysql.jdbc.Driver");
            DriverManager.registerDriver(driver);
            System.out.println("Driver successfully registered!");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        //out.println("<form> )
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}