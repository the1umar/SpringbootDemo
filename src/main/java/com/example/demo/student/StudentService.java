package com.example.demo.student;

// SERVICE (BUSINESS LOGIC) LAYER
// Handles all the logic, handles business logic bulk to keep the actual API app simple
//
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service // @Component works too
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll(); // findAll method retrieves all entities of a specific type from the underlying database
    }
}
