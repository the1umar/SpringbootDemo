package com.example.demo.student;

import java.time.LocalDate;

public class Student {

    private String email;
    private Long id;
    private String name;
    private Integer age;
    private LocalDate dob;

    public Student () {
    }

    public Student(String email, Long id, String name, Integer age, LocalDate dob) {
        this.email = email;
        this.id = id;
        this.name = name;
        this.age = age;
        this.dob = dob;
    }

    public Student (String email, String name, Integer age, LocalDate dob) {
        this.email = email;
        this.name = name;
        this.age = age;
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
        return age;
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
