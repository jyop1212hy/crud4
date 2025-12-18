package com.crud4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Crud4Application {

    public static void main(String[] args) {
        SpringApplication.run(Crud4Application.class, args);
    }

}
