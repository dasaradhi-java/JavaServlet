package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/test")
    public class MyServlet1 extends HttpServlet {
        private static final long serialVersionUID = 1L;

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            response.setContentType("text/html");

            try {
//                InputStream input = getClass().getClassLoader().getResourceAsStream("index.html");
    //
//                if (input != null) {
//                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(input));
//                         PrintWriter out = response.getWriter()) {
    //
//                        String line;
//                        while ((line = reader.readLine()) != null) {
//                            out.println(line);
//                        }
//                    }
//                } else {
//                    response.getWriter().println("index.html not found!");
//                }
        //    	request.getRequestDispatcher("/index.html").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
                response.getWriter().println("An error occurred: " + e.getMessage());
            }
        }

    }