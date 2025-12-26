package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.model.ExamRoom;
import com.example.demo.model.ExamSession;
import com.example.demo.model.SeatingPlan;
import com.example.demo.model.Student;
import com.example.demo.repository.ExamRoomRepository;
import com.example.demo.repository.ExamSessionRepository;
import com.example.demo.repository.SeatingPlanRepository;
import com.example.demo.service.SeatingPlanService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class SeatingPlanServiceImpl implements SeatingPlanService {

    private final ExamSessionRepository sessionRepo;
    private final SeatingPlanRepository planRepo;
    private final ExamRoomRepository roomRepo;

    public SeatingPlanServiceImpl(
            ExamSessionRepository sessionRepo,
            SeatingPlanRepository planRepo,
            ExamRoomRepository roomRepo
    ) {
        this.sessionRepo = sessionRepo;
        this.planRepo = planRepo;
        this.roomRepo = roomRepo;
    }

    @Override
    public SeatingPlan generatePlan(Long sessionId) {

        ExamSession session = sessionRepo.findById(sessionId)
                .orElseThrow(() -> new ApiException("Session not found"));

        List<Student> students = new ArrayList<>(session.getStudents());

        List<ExamRoom> rooms = roomRepo.findAll();
        if (rooms.isEmpty()) {
            throw new ApiException("No room available");
        }

        ExamRoom selectedRoom = null;
        for (ExamRoom r : rooms) {
            if (r.getCapacity() >= students.size()) {
                selectedRoom = r;
                break;
            }
        }

        if (selectedRoom == null) {
            throw new ApiException("No room available");
        }

        // seat mapping: seat-1 -> rollNumber
        Map<String, String> arrangement = new LinkedHashMap<>();
        for (int i = 0; i < students.size(); i++) {
            arrangement.put("seat-" + (i + 1), students.get(i).getRollNumber());
        }

        String json;
        try {
            json = new ObjectMapper().writeValueAsString(arrangement);
        } catch (Exception e) {
            json = "{}";
        }

        SeatingPlan plan = new SeatingPlan();
        plan.setExamSession(session);
        plan.setRoom(selectedRoom);
        plan.setArrangementJson(json);
        plan.setGeneratedAt(LocalDateTime.now());

        return planRepo.save(plan);
    }

    @Override
    public SeatingPlan getPlan(Long id) {
        return planRepo.findById(id)
                .orElseThrow(() -> new ApiException("Plan not found"));
    }

    @Override
    public List<SeatingPlan> getPlansBySession(Long sessionId) {
        return planRepo.findByExamSessionId(sessionId);
    }
}
