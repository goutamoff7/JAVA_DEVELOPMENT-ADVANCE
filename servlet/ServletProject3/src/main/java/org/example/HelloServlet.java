package org.example;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class HelloServlet extends HttpServlet {

//    @Override
//    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        // Tell the browser that the response is HTML, not plain text
//        response.setContentType("text/html");
//
//        // Get the output writer for sending data back to the client (browser)
//        PrintWriter out = response.getWriter();
//
//        // Write a simple HTML snippet as the response body
//        out.println("<h2><b>Hello World</b></h2>");
//    }

//        Why service() works for all HTTP methods
//    HttpServlet defines two important methods:
//    1. service(HttpServletRequest, HttpServletResponse)
//    2. doGet(), doPost(), doPut(), doDelete(), etc.
//
//        The default implementation in HttpServlet is:
//    When a request comes in, Tomcat calls your servlet’s service() method.
//    The service() method in HttpServlet checks the request’s HTTP method (GET, POST, etc.)
//    and forwards it to the corresponding method (doGet(), doPost(), etc.).
//
//    But if we override service() directly (like we did), then:
//    Tomcat still calls your service() method first.
//    Since we replaced it, your code will run for every HTTP method (GET, POST, PUT, DELETE…)
//    unless we manually check request.getMethod() inside.
//
// That’s why your Hello World prints no matter if you send a GET, POST, or any other HTTP request.
//
//      Best practice
//
//    Use doGet() when you only want to handle GET requests (typical for browsers visiting a page).//
//    Use doPost() for form submissions or API posts.
//
//    Override service() only when we want a single handler for all HTTP methods or
//    want to do some common pre-processing before dispatching.


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h2><b>Hello World</b></h2>");
    }

}
