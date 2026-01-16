package com.example.demo.student;

// DATA ACCESS LAYER
// Essentially our 'database API'
// This layer exists to separate the data we want from how the database works
// Can later change the database (PSQL) or the ORM (Hibernate) without having to change the controller or services
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
