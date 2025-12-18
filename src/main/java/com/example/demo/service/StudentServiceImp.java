package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;

@Service
public class StudentServiceImp implements StudentService {
    @Autowired
    StudentRepository studrepo;

    @Override
    public Student addstudent(Student st) {
        return studrepo.save(st);
    }

    public List<Student> getallstudents(){
        return studrepo.findAll();
    }
}


