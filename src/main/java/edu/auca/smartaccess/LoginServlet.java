package edu.auca.smartaccess;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;


public class LoginServlet extends HttpServlet{

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        out.println("<html><body style='font-family:Arial,sans-serif;text-align:center;margin-top:50px;'>");

        if (password.length() < 8) {
            out.println("<h3>Hello " + username + ", your password is weak. Try a strong one.</h3>");
        } else {
            out.println("<h3>Welcome " + username + "!</h3>");
        }

        out.println("</body></html>");
    }  
}
