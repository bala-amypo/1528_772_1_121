package com.example.demo.controller;

import com.example.demo.model.SeatingPlan;
import com.example.demo.service.SeatingPlanService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plans")
public class SeatingPlanController {

    private final SeatingPlanService ctrl;

    public SeatingPlanController(SeatingPlanService ctrl) {
        this.ctrl = ctrl;
    }

    @PostMapping("/{sessionId}")
    public SeatingPlan generate(@PathVariable Long sessionId) {
        return ctrl.generatePlan(sessionId);
    }

    @GetMapping("/{id}")
    public SeatingPlan get(@PathVariable Long id) {
        return ctrl.getPlan(id);
    }

    @GetMapping("/session/{sessionId}")
    public List<SeatingPlan> getBySession(@PathVariable Long sessionId) {
        return ctrl.getPlansBySession(sessionId);
    }
}
