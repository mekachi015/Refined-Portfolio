package com.backend.Portfolio_Backend.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class DotenvConfig {

    static {
        try {
            Dotenv dotenv = Dotenv.configure()
                .ignoreIfMalformed()
                .ignoreIfMissing()
                .load();
            
            dotenv.entries().forEach(entry -> {
                System.setProperty(entry.getKey(), entry.getValue());
            });
            
            System.out.println("Environment variables loaded from .env file");
        } catch (Exception e) {
            System.out.println("No .env file found or error loading it: " + e.getMessage());
        }
    }
}