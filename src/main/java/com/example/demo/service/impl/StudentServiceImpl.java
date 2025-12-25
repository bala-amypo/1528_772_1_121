package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repo;

    public StudentServiceImpl(StudentRepository repo) {
        this.repo = repo;
    }

    @Override
    public Student add(Student student) {
        if (student.getRollNumber() == null) {
            throw new ApiException("Invalid student");
        }
        if (repo.findByRollNumber(student.getRollNumber()).isPresent()) {
            throw new ApiException("Roll number exists");
        }
        return repo.save(student);
    }

    @Override
    public List<Student> list() {
        return repo.findAll();
    }

    @Override
    public Student get(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ApiException("Student not found"));
    }
}
