package com.capitole;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.capitole.domain.puerto")
public class CapitoleApplication {
    public static void main(String[] args) {
        SpringApplication.run(CapitoleApplication.class, args);
    }
}
