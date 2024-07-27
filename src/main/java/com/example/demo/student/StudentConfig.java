package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
//            Student ram = new Student(
//                    "Ram",
//                    "ram.com",
//                    LocalDate.of(2000, Month.JANUARY, 1)
//            );
//
//            Student shyam = new Student(
//                    "Shyam",
//                    "shyam.com",
//                    LocalDate.of(1996, Month.FEBRUARY, 4)
//            );
//
//            studentRepository.saveAll(List.of(ram, shyam));
        };
    }
}
