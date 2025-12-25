package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.model.ExamRoom;
import com.example.demo.repository.ExamRoomRepository;
import com.example.demo.service.ExamRoomService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamRoomServiceImpl implements ExamRoomService {

    private final ExamRoomRepository repo;

    public ExamRoomServiceImpl(ExamRoomRepository repo) {
        this.repo = repo;
    }

    @Override
    public ExamRoom add(ExamRoom room) {
        if (repo.findByRoomNumber(room.getRoomNumber()).isPresent()) {
            throw new ApiException("Room exists");
        }
        room.ensureCapacityMatches();
        return repo.save(room);
    }

    @Override
    public List<ExamRoom> list() {
        return repo.findAll();
    }

    @Override
    public ExamRoom get(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ApiException("Room not found"));
    }
}
