package com.example.demo.service;

import com.example.demo.exception.ApiException;
import com.example.demo.model.ExamRoom;
import com.example.demo.repository.ExamRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamRoomServiceImpl implements ExamRoomService {

    @Autowired
    private ExamRoomRepository examRoomRepository;

    @Override
    public ExamRoom addRoom(ExamRoom room) {

        if (room.getRows() == null || room.getColumns() == null
                || room.getRows() <= 0 || room.getColumns() <= 0) {
            throw new ApiException("Invalid rows or columns");
        }

        if (examRoomRepository.findByRoomNumber(room.getRoomNumber()).isPresent()) {
            throw new ApiException("Room number already exists");
        }

        room.ensureCapacityMatches();

        return examRoomRepository.save(room);
    }

    @Override
    public List<ExamRoom> getAllRooms() {
        return examRoomRepository.findAll();
    }
}
