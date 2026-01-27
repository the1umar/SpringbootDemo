package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration // "this class configures something"
public class StudentConfig {

    @Bean // creates a spring-managed component
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        // This code runs ONCE when app starts
        // Creates and saves students
        return args -> {
            Student mariam = new Student (
                    "mariam.jamal@gmail.com",
                    "Mariam",
                    LocalDate.of(2000, Month.JANUARY, 5)
            );

            Student alex = new Student (
                    "alex@gmail.com",
                    "Alex",
                    LocalDate.of(2004, Month.JANUARY, 5)
            );

            repository.saveAll(
                    List.of(mariam, alex)
            );
        };
    }
}
