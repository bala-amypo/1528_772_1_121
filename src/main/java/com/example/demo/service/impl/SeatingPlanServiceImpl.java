package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.model.SeatingPlan;
import com.example.demo.repository.SeatingPlanRepository;
import com.example.demo.service.SeatingPlanService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatingPlanServiceImpl implements SeatingPlanService {

    private final SeatingPlanRepository planRepo;
    private final ExamSessionRepository sessionRepo;
    private final ExamRoomRepository roomRepo;

    public SeatingPlanServiceImpl(
            SeatingPlanRepository planRepo,
            ExamSessionRepository sessionRepo,
            ExamRoomRepository roomRepo) {
        this.planRepo = planRepo;
        this.sessionRepo = sessionRepo;
        this.roomRepo = roomRepo;
    }

    public SeatingPlan generatePlan(Long sessionId) {
        SeatingPlan plan = new SeatingPlan();
        plan.setExamSession(sessionRepo.findById(sessionId).orElseThrow());
        return planRepo.save(plan);
    }

    public SeatingPlan getPlan(Long id) {
        return planRepo.findById(id).orElseThrow();
    }

    public List<SeatingPlan> getPlansBySession(Long sessionId) {
        return planRepo.findByExamSessionId(sessionId);
    }
}

