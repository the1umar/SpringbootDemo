package com.example.demo.student;

// SERVICE LAYER
// Handles all the logic, can take flexibility to keep the actual API app simple

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service // @Component works too
public class StudentService {
    public List<Student> getStudents() {
        return List.of(
                new Student("afolamikorede@gmail.com",
                        1L,
                        "Umar Afolami",
                        21,
                        LocalDate.of(2004, Month.JUNE, 2))
        );
    }
}
