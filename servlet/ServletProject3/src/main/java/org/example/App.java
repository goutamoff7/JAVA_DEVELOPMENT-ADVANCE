package org.example;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

public class App {
    public static void main(String[] args) throws LifecycleException {
        // Create a new embedded Tomcat server instance
        Tomcat tomcat = new Tomcat();

        // Set the port on which Tomcat will listen (default is 8080, but we make it explicit at 8081)
        // Port must be set before getConnector()
        tomcat.setPort(8081);

        // Force creation of the default HTTP connector (needed so Tomcat can listen on a port)
        tomcat.getConnector();


        // Create a new web application context
        // "" -> root context path (http://localhost:8080/)
        // null -> base directory for the webapp (normally /WEB-INF etc., here not needed)
        Context context = tomcat.addContext("", null);

        // Register a new servlet named "hs" and map it to our HelloServlet class
        Tomcat.addServlet(context, "hs", new HelloServlet());

        // Map the servlet "hs" to URL path "/hello"
        // So when user visits http://localhost:8080/hello, HelloServlet will be executed
        context.addServletMappingDecoded("/hello", "hs");

        // Start the embedded Tomcat server
        tomcat.start();

        // Keep the server alive and wait for incoming requests
        // Without this, the program would exit immediately after starting
        tomcat.getServer().await();
    }
}
