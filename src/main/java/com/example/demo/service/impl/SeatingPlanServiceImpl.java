package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.SeatingPlanService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatingPlanServiceImpl implements SeatingPlanService {

    private final ExamSessionRepository examSessionRepository;
    private final SeatingPlanRepository seatingPlanRepository;
    private final ExamRoomRepository examRoomRepository;

    private final ObjectMapper mapper = new ObjectMapper();

    public SeatingPlanServiceImpl(ExamSessionRepository examSessionRepository,
                                  SeatingPlanRepository seatingPlanRepository,
                                  ExamRoomRepository examRoomRepository) {
        this.examSessionRepository = examSessionRepository;
        this.seatingPlanRepository = seatingPlanRepository;
        this.examRoomRepository = examRoomRepository;
    }

    @Override
    public SeatingPlan generatePlan(Long sessionId) {

        ExamSession session = examSessionRepository.findById(sessionId)
                .orElseThrow(() -> new ApiException("Session not found"));

        int studentCount = session.getStudents().size();

        ExamRoom selectedRoom = null;
        for (ExamRoom room : examRoomRepository.findAll()) {
            if (room.getCapacity() >= studentCount) {
                selectedRoom = room;
                break;
            }
        }

        if (selectedRoom == null) {
            throw new ApiException("No room available");
        }

        String arrangementJson = buildArrangementJson(session, selectedRoom);

        SeatingPlan plan = new SeatingPlan();
        plan.setExamSession(session);
        plan.setRoom(selectedRoom);
        plan.setArrangementJson(arrangementJson);

        return seatingPlanRepository.save(plan);
    }

    private String buildArrangementJson(ExamSession session, ExamRoom room) {

        try {
            ObjectNode root = mapper.createObjectNode();
            ArrayNode seats = mapper.createArrayNode();

            int seatIndex = 1;
            for (Student student : session.getStudents()) {
                ObjectNode seat = mapper.createObjectNode();
                seat.put("seatNumber", seatIndex++);
                seat.put("rollNumber", student.getRollNumber());
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
        return seatingPlanRepository.findById(planId)
                .orElseThrow(() -> new ApiException("Plan not found"));
    }

    @Override
    public List<SeatingPlan> getPlansBySession(Long sessionId) {
        return seatingPlanRepository.findByExamSessionId(sessionId);
    }
}
