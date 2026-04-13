/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus;
import com.smartcampus.config.ApplicationConfig;
import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.glassfish.jersey.servlet.ServletContainer;


/**
 *
 * @author kulanitennakoon
 */

public class Main {
    public static void main(String[] args) throws Exception {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);

        Context context = tomcat.addContext("", null);

        Tomcat.addServlet(context, "jersey-servlet", new ServletContainer(new ApplicationConfig()));
        context.addServletMappingDecoded("/*", "jersey-servlet");

        tomcat.start();

        System.out.println("Smart Campus API is running at:");
        System.out.println("http://localhost:8080/api/v1/");
        System.out.println("Press Ctrl + C to stop.");

        tomcat.getServer().await();
    }
    
}
