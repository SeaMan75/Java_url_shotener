package com.url.shorter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.url.shorter")
public class ShorterApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShorterApplication.class, args);
    }
}
