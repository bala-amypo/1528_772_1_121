package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.ExamRoom;

@Service
public interface ExamRoomService {
    ExamRoom addRoom(ExamRoom room);
    List<ExamRoom> getAllRooms();
}
