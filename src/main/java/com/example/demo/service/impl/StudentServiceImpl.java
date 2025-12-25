package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repository;

    public StudentServiceImpl(StudentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Student addStudent(Student student) {

        if (student.getRollNumber() == null || student.getRollNumber().isEmpty()) {
            throw new ApiException("Invalid student roll number");
        }

        if (repository.findByRollNumber(student.getRollNumber()).isPresent()) {
            throw new ApiException("Roll number already exists");
        }

        if (student.getYear() == null || student.getYear() < 1 || student.getYear() > 5) {
            throw new ApiException("Invalid year");
        }

        return repository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return repository.findAll();
    }
}
