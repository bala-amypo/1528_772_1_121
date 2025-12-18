package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.StudentEntity;
import com.example.demo.repository.StudentRepository;

@Service
public class StudentServiceImp implements StudentService {
    @Autowired
    StudentRepository studrepo;

    @Override
    public StudentEntity addstudent(StudentEntity st) {
        return studrepo.save(st);
    }

    public List<StudentEntity> getallstudents(){
        return studrepo.findAll();
    }
}


