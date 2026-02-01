package edu.auca.smartaccess;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class RedirectServlet extends HttpServlet{


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String query = request.getParameter("query");
        String url = "https://www.google.com/search?q=";

        if (query != null && !query.isEmpty()) {
            url += query.replace(" ", "+"); // simple URL encoding for spaces
        }

        response.sendRedirect(url);
    }

}
