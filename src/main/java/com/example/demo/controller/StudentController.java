package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.StudentEntity;
import com.example.demo.service.StudentService;

@RestController
public class StudentController {
    @Autowired
    StudentService st;

    @PostMapping("/addstudent")
    public StudentEntity addstud(@RequestBody StudentEntity student){
        return st.addstudent(student);
    }
    
    @GetMapping("/getallstudents")
    public List<StudentEntity> getstud(){
        return st.getallstudents();
    }
}


