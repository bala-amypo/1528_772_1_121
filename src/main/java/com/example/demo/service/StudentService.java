package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.StudentEntity;

@Service
public interface StudentService {
    StudentEntity addstudent(StudentEntity st);
    List<StudentEntity> getallstudents();
}


