package com.example.demo.controller;

import com.example.demo.model.ExamSession;
import com.example.demo.service.ExamSessionService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ExamSession> create(@RequestBody ExamSession session) {
        return ResponseEntity.ok(ctrl.create(session));
    }

    @GetMapping
    public ResponseEntity<List<ExamSession>> list() {
        return ResponseEntity.ok(ctrl.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExamSession> get(@PathVariable Long id) {
        return ResponseEntity.ok(ctrl.get(id));
    }
}
