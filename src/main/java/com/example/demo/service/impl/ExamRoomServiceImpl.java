package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.model.ExamRoom;
import com.example.demo.repository.ExamRoomRepository;
import com.example.demo.service.ExamRoomService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamRoomServiceImpl implements ExamRoomService {

    private final ExamRoomRepository repository;

    public ExamRoomServiceImpl(ExamRoomRepository repository) {
        this.repository = repository;
    }

    @Override
    public ExamRoom addRoom(ExamRoom room) {

        if (room.getRoomNumber() == null || room.getRoomNumber().trim().isEmpty()) {
            throw new ApiException("Room number is required");
        }

        if (room.getRows() == null || room.getColumns() == null
                || room.getRows() <= 0 || room.getColumns() <= 0) {
            throw new ApiException("Invalid rows or columns");
        }

        if (repository.findByRoomNumber(room.getRoomNumber()).isPresent()) {
            throw new ApiException("Room number already exists");
        }

        // ALWAYS enforce capacity
        room.ensureCapacityMatches();

        return repository.save(room);
    }

    @Override
    public List<ExamRoom> getAllRooms() {
        return repository.findAll();
    }

    @Override
    public ExamRoom getRoom(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ApiException("Room not found"));
    }
}
