package com;  // Package declaration

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class for starting the Spring Boot application.
 */
@SpringBootApplication  // Enables Spring Boot auto-configuration and component scanning
public class MainApp {

    /**
     * Main method to launch the application.
     */
    public static void main(String[] args) {
        SpringApplication.run(MainApp.class, args);  // Starts the Spring Boot application
    }
}
