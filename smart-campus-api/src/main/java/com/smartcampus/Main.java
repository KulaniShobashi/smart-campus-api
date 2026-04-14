/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus;

import com.smartcampus.config.ApplicationConfig;
import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.glassfish.jersey.servlet.ServletContainer;

public class Main {

    public static void main(String[] args) {
        try {
            Tomcat tomcat = new Tomcat();
            tomcat.setPort(8081);
            tomcat.setBaseDir("tomcat-temp");

            // IMPORTANT: force Tomcat to create the connector
            tomcat.getConnector();

            Context context = tomcat.addContext("", new java.io.File(".").getAbsolutePath());

            Tomcat.addServlet(context, "jersey-servlet", new ServletContainer(new ApplicationConfig()));
            context.addServletMappingDecoded("/*", "jersey-servlet");

            tomcat.start();

            System.out.println("=== SERVER STARTED SUCCESSFULLY ===");
            System.out.println("http://localhost:8081/api/v1/");
            System.out.println("Do not close this Run tab.");

            tomcat.getServer().await();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}