package com.example.demo.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

// HTTP/API layer
// this class will include support for our API (for students)
// This class listens for browser/client requests
@RestController // Essentially signifying “This class handles HTTP requests”
@RequestMapping(path = "/api/v1/student") // base URL for this controller
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService; // this is a dependency injection (we never say 'new StudentService()')
    }

    @GetMapping // handles get operations
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @PostMapping
    public void registerStudent(@RequestBody Student student) { // take from post request body and map to our Student param
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId) {
        studentService.deleteStudent(studentId);
    }

    @PutMapping(path = "{studentId}")
    public void updateStudent(
            @PathVariable("studentId") Long studentId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email
            ) {
        studentService.updateStudent(studentId, name, email);
    }
}
