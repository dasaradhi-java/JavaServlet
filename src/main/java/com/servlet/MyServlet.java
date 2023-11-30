//package com.servlet;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.PrintWriter;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//@WebServlet("/")
//public class MyServlet extends HttpServlet {
//    private static final long serialVersionUID = 1L;
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html");
//
//        try {
////            InputStream input = getClass().getClassLoader().getResourceAsStream("index.html");
////
////            if (input != null) {
////                try (BufferedReader reader = new BufferedReader(new InputStreamReader(input));
////                     PrintWriter out = response.getWriter()) {
////
////                    String line;
////                    while ((line = reader.readLine()) != null) {
////                        out.println(line);
////                    }
////                }
////            } else {
////                response.getWriter().println("index.html not found!");
////            }
//        	response.sendRedirect(request.getContextPath() +"/index.html");
//        } catch (Exception e) {
//            e.printStackTrace();
//            response.getWriter().println("An error occurred: " + e.getMessage());
//        }
//    
//    }
//}