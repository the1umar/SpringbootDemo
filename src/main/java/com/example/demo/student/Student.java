package com.example.demo.student;

import jakarta.persistence.*; // always use persistence so if we change hibernate everything still follows

import java.time.LocalDate;
import java.time.Period;

@Entity
@Table
public class Student {
    // Delegate identity (id) creation in the database

    @Id // indicated primary key
    @SequenceGenerator ( // defines how ID's are created.
            name = "student_sequence", // internal Hibernate (ORM) name
            sequenceName = "student_sequence", // actual database sequence name
            allocationSize = 1 // how many IDs hibernate grabs at once
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    private Long id; // the field that the above annotations is attached to (hence primary key)
    private String email;
    private String name;
    @Transient // meaning age doesn't have to be ea column (will be auto calculated)
    private Integer age;
    private LocalDate dob;

    public Student () {
    }

    public Student (String email, String name, LocalDate dob) {
        this.email = email;
        this.name = name;
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Integer getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "email='" + email + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", dob=" + dob +
                '}';
    }
}
