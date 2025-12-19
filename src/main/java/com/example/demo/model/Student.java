package com.example.demo.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;


@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String rollNumber;
    private String name;
    private String department;
    private Integer year;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getRollNumber() {
        return rollNo;
    }
    public void setRollNumber(String rollNo) {
        this.rollNo = rollNo;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public int getYear() {
        return year;
    }
    public void setYear(Integer year) {
        this.year = year;
    }
    public Student(Long id, String rollNo, String name, String department, Integer year) {
        this.id = id;
        this.rollNo = rollNo;
        this.name = name;
        this.department = department;
        this.year = year;
    }
    public Student() {
    } 
}