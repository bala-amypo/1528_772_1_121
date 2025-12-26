package com.example.demo.controller;

import com.example.demo.model.SeatingPlan;
import com.example.demo.service.SeatingPlanService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<SeatingPlan> generate(@PathVariable Long sessionId) {
        return ResponseEntity.ok(ctrl.generatePlan(sessionId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SeatingPlan> get(@PathVariable Long id) {
        return ResponseEntity.ok(ctrl.getPlan(id));
    }

    @GetMapping("/session/{sessionId}")
    public ResponseEntity<List<SeatingPlan>> list(@PathVariable Long sessionId) {
        return ResponseEntity.ok(ctrl.getPlansBySession(sessionId));
    }
}
