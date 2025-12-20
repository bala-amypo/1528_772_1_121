package com.example.demo.service;

import com.example.demo.exception.ApiException;
import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student addStudent(Student student) {

        if (student.getRollNumber() == null || student.getRollNumber().isEmpty()) {
            throw new ApiException("Invalid student roll number");
        }

        if (studentRepository.findByRollNumber(student.getRollNumber()).isPresent()) {
            throw new ApiException("Roll number already exists");
        }

        if (student.getYear() == null || student.getYear() < 1 || student.getYear() > 5) {
            throw new ApiException("Invalid year");
        }

        return studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}
