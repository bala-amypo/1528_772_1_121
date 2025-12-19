package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.ExamRoom;
import com.example.demo.service.ExamRoomService;

@RestController
public class ExamRoomController {
    @Autowired
    ExamRoomService ers;

    @PostMapping("/getroom")
    public ExamRoom addRm(@RequestBody ExamRoom room){
        return ers.addRoom(room);
    }

    @GetMapping("/listroom")
    public List<ExamRoom> getRooms(){
        return ers.getAllRooms();
    }
}
