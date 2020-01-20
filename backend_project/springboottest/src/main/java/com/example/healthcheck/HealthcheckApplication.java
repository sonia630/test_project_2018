package com.example.healthcheck;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.DispatcherServlet;

@SpringBootApplication
public class HealthcheckApplication {

    public static void main(String[] args) {
        SpringApplication.run(HealthcheckApplication.class, args);
    }


}
