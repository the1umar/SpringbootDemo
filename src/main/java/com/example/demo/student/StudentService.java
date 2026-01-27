package com.example.demo.student;

// SERVICE (BUSINESS LOGIC) LAYER
// Handles all the logic, handles business logic bulk to keep the actual API app simple
//
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service // @Component works too
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired // for dependency injection
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll(); // findAll method retrieves all entities of a specific type from the underlying database
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("Student email already exists");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if (!exists) {
            throw new IllegalStateException("Student with id " + studentId + " does not exist");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        // Find the student
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException(
                        "Student with id " + studentId + " does not exist"
                ));

        // Update name if provided and different
        if (name != null && !name.isEmpty() && !name.equals(student.getName())) {
            student.setName(name);
        }

        // Update email if provided, different, and not taken
        if (email != null && !email.isEmpty() && !email.equals(student.getEmail())) {
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if (studentOptional.isPresent()) {
                throw new IllegalStateException("Email already taken");
            }
            student.setEmail(email);
        }

        //  NO NEED to call repository.save()!
        // Because of @Transactional, changes are automatically saved
    }
}
