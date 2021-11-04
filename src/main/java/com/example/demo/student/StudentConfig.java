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
    CommandLineRunner commandLineRunner(StudentRepository studentRepository){
        return args->{
            Student Gwen = new Student("Gwyneth Pena", "gwenpena21@gmail.com", LocalDate.of(2000, Month.MARCH, 14));
            studentRepository.saveAll(List.of(Gwen));
        };

    }
}
