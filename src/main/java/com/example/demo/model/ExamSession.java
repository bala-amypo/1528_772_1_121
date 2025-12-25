package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "exam_sessions")
public class ExamSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String courseCode;
    private LocalDate examDate;
    private String examTime;

    @ManyToMany
    private Set<Student> students;

    public ExamSession() {}

    // getters
    public Long getId() { return id; }
    public String getCourseCode() { return courseCode; }
    public LocalDate getExamDate() { return examDate; }
    public String getExamTime() { return examTime; }
    public Set<Student> getStudents() { return students; }

    // setters
    public void setId(Long id) { this.id = id; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }
    public void setExamDate(LocalDate examDate) { this.examDate = examDate; }
    public void setExamTime(String examTime) { this.examTime = examTime; }
    public void setStudents(Set<Student> students) { this.students = students; }

    // ===== BUILDER =====
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final ExamSession s = new ExamSession();

        public Builder id(Long id) { s.setId(id); return this; }
        public Builder courseCode(String c) { s.setCourseCode(c); return this; }
        public Builder examDate(LocalDate d) { s.setExamDate(d); return this; }
        public Builder examTime(String t) { s.setExamTime(t); return this; }
        public Builder students(Set<Student> st) { s.setStudents(st); return this; }

        public ExamSession build() { return s; }
    }
}
