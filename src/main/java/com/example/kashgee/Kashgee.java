package com.example.kashgee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Kashgee {

    public static void main(String[] args) {
        SpringApplication.run(Kashgee.class, args);
        System.out.println("Spring Boot application started successfully!");
        System.out.println("Open your browser and navigate to: http://localhost:8081");
    }
}