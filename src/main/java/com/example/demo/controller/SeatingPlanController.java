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

    @PostMapping("/{sessionId}")
    public SeatingPlan create(@PathVariable Long sessionId) {
        return service.create(sessionId);
    }

    @GetMapping
    public List<SeatingPlan> list() {
        return service.list();
    }

    @GetMapping("/{id}")
    public SeatingPlan get(@PathVariable Long id) {
        return service.get(id);
    }
}
