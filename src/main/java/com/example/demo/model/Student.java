package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String rollNumber;

    private String name;
    private String department;
    private Integer year;

    public Student() {}

    public Long getId() { return id; }
    public String getRollNumber() { return rollNumber; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public Integer getYear() { return year; }

    public void setId(Long id) { this.id = id; }
    public void setRollNumber(String rollNumber) { this.rollNumber = rollNumber; }
    public void setName(String name) { this.name = name; }
    public void setDepartment(String department) { this.department = department; }
    public void setYear(Integer year) { this.year = year; }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final Student s = new Student();

        public Builder id(Long id) { s.setId(id); return this; }
        public Builder rollNumber(String rollNumber) { s.setRollNumber(rollNumber); return this; }
        public Builder name(String name) { s.setName(name); return this; }
        public Builder department(String department) { s.setDepartment(department); return this; }
        public Builder year(Integer year) { s.setYear(year); return this; }

        public Student build() { return s; }
    }
}
