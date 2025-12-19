package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class ExamRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String rollnumber;

    private String name;
    private String department;
    private Integer year;
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getRollnumber() {
        return rollnumber;
    }
    public void setRollnumber(String rollnumber) {
        this.rollnumber = rollnumber;
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
    public Integer getYear() {
        return year;
    }
    public void setYear(Integer year) {
        this.year = year;
    }
    public ExamRoom(long id, String rollnumber, String name, String department, Integer year) {
        this.id = id;
        this.rollnumber = rollnumber;
        this.name = name;
        this.department = department;
        this.year = year;
    }
    public ExamRoom() {
    }
}
