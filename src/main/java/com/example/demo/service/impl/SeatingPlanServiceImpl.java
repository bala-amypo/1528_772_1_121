package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.model.ExamRoom;
import com.example.demo.model.ExamSession;
import com.example.demo.model.SeatingPlan;
import com.example.demo.repository.ExamRoomRepository;
import com.example.demo.repository.ExamSessionRepository;
import com.example.demo.repository.SeatingPlanRepository;
import com.example.demo.service.SeatingPlanService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatingPlanServiceImpl implements SeatingPlanService {

    private final SeatingPlanRepository planRepository;
    private final ExamSessionRepository sessionRepository;
    private final ExamRoomRepository roomRepository;

    public SeatingPlanServiceImpl(
            SeatingPlanRepository planRepository,
            ExamSessionRepository sessionRepository,
            ExamRoomRepository roomRepository) {
        this.planRepository = planRepository;
        this.sessionRepository = sessionRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    public SeatingPlan generatePlan(Long sessionId) {

        ExamSession session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new ApiException("Session not found"));

        List<ExamRoom> rooms = roomRepository.findAll();
        if (rooms.isEmpty()) {
            throw new ApiException("No rooms available");
        }

        SeatingPlan plan = new SeatingPlan();
        plan.setExamSession(session);
        plan.setRoom(rooms.get(0));
        plan.setArrangementJson("{}");

        return planRepository.save(plan);
    }

    @Override
    public SeatingPlan getPlan(Long id) {
        return planRepository.findById(id)
                .orElseThrow(() -> new ApiException("Plan not found"));
    }

    @Override
    public List<SeatingPlan> getPlansBySession(Long sessionId) {
        return planRepository.findByExamSessionId(sessionId);
    }
}
