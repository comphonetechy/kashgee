package com.example.helloworldapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloWorldApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloWorldApplication.class, args);
        System.out.println("Spring Boot application started successfully!");
        System.out.println("Open your browser and navigate to: http://localhost:8081");
    }
}