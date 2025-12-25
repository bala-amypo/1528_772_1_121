package com.example.demo.controller;

import com.example.demo.model.ExamSession;
import com.example.demo.service.ExamSessionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sessions")
public class ExamSessionController {

    private final ExamSessionService service;

    public ExamSessionController(ExamSessionService service) {
        this.service = service;
    }

    @PostMapping
    public ExamSession createSession(@RequestBody ExamSession session) {
        return service.createSession(session);
    }

    @GetMapping("/{id}")
    public ExamSession getSession(@PathVariable Long id) {
        return service.getSession(id);
    }
}
