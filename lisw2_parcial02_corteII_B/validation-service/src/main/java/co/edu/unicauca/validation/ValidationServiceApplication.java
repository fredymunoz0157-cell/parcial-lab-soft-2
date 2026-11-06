package co.edu.unicauca.validation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the Validation Microservice running on port 8082.
 */
@SpringBootApplication
public class ValidationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ValidationServiceApplication.class, args);
    }
}
