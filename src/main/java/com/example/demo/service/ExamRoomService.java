package com.example.demo.service;

import com.example.demo.model.ExamRoom;
import java.util.List;

public interface ExamRoomService {
    ExamRoom add(ExamRoom room);
    List<ExamRoom> list();
    ExamRoom get(Long id);
}
