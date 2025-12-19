package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.ExamRoom;
import com.example.demo.repository.ExamRoomRepository;

@Service
public class ExamRoomServiceImp implements ExamRoomService{
    @Autowired
    ExamRoomRepository emrepo;

    @Override
    public ExamRoom addRoom(ExamRoom room){
        return emrepo.save(room);
    }

    public List<ExamRoom> getAllRooms(){
        return emrepo.findAll();
    }
}
