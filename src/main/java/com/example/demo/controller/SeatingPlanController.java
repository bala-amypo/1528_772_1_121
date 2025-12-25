package com.example.demo.controller;

import com.example.demo.model.SeatingPlan;
import com.example.demo.service.SeatingPlanService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plans")
public class SeatingPlanController {

    private final SeatingPlanService service;

    public SeatingPlanController(SeatingPlanService service) {
        this.service = service;
    }

    @PostMapping("/generate/{sessionId}")
    public SeatingPlan generatePlan(@PathVariable Long sessionId) {
        return service.generatePlan(sessionId);
    }

    @GetMapping("/{id}")
    public SeatingPlan getPlan(@PathVariable Long id) {
        return service.getPlan(id);
    }

    @GetMapping("/session/{sessionId}")
    public List<SeatingPlan> getPlansBySession(@PathVariable Long sessionId) {
        return service.getPlansBySession(sessionId);
    }
}
