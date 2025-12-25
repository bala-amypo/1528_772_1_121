package com.example.demo.controller;

import com.example.demo.model.ExamSession;
import com.example.demo.service.ExamSessionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sessions")
public class ExamSessionController {

    private final ExamSessionService ctrl;

    public ExamSessionController(ExamSessionService ctrl) {
        this.ctrl = ctrl;
    }

    @PostMapping
    public ExamSession create(@RequestBody ExamSession s) {
        return ctrl.createSession(s);
    }

    @GetMapping("/{id}")
    public ExamSession get(@PathVariable Long id) {
        return ctrl.getSession(id);
    }

    @GetMapping
    public List<ExamSession> list() {
        return ctrl.listSessions();
    }
}
