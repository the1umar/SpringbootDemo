package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

// this class will include support for our API (for students)

// API layer

// This class listens for browser/client requests
@RestController // Essentially signifying “This class handles HTTP requests”
@RequestMapping(path = "/api/v1/student") // base URL for this controller
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService; // this is a dependency injection (we never say 'new StudentService()')
    }

    @GetMapping
    public List<Student> getStudents() {
        return studentService.getStudents();
    }
}
