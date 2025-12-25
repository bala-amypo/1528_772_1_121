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
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatingPlanServiceImpl implements SeatingPlanService {

    private final ExamSessionRepository sessionRepo;
    private final SeatingPlanRepository planRepo;
    private final ExamRoomRepository roomRepo;
    private final ObjectMapper mapper = new ObjectMapper();

    public SeatingPlanServiceImpl(
            ExamSessionRepository sessionRepo,
            SeatingPlanRepository planRepo,
            ExamRoomRepository roomRepo) {
        this.sessionRepo = sessionRepo;
        this.planRepo = planRepo;
        this.roomRepo = roomRepo;
    }

    @Override
    public SeatingPlan generatePlan(Long sessionId) {

        ExamSession session = sessionRepo.findById(sessionId)
                .orElseThrow(() -> new ApiException("Session not found"));

        int studentCount = session.getStudents().size();

        ExamRoom room = roomRepo.findAll().stream()
                .filter(r -> r.getCapacity() >= studentCount)
                .findFirst()
                .orElseThrow(() -> new ApiException("No room available"));

        String json = buildJson(session, room);

        SeatingPlan plan = new SeatingPlan();
        plan.setExamSession(session);
        plan.setRoom(room);
        plan.setArrangementJson(json);

        return planRepo.save(plan);
    }

    private String buildJson(ExamSession session, ExamRoom room) {
        try {
            ObjectNode root = mapper.createObjectNode();
            ArrayNode seats = mapper.createArrayNode();

            int i = 1;
            for (Student s : session.getStudents()) {
                ObjectNode seat = mapper.createObjectNode();
                seat.put("seatNumber", i++);
                seat.put("rollNumber", s.getRollNumber());
                seats.add(seat);
            }

            root.set("seats", seats);
            root.put("capacity", room.getCapacity());

            return mapper.writeValueAsString(root);
        } catch (Exception e) {
            throw new ApiException("Invalid seating arrangement");
        }
    }

    @Override
    public SeatingPlan getPlan(Long planId) {
        return planRepo.findById(planId)
                .orElseThrow(() -> new ApiException("Plan not found"));
    }

    @Override
    public List<SeatingPlan> getPlansBySession(Long sessionId) {
        return planRepo.findByExamSessionId(sessionId);
    }
}
